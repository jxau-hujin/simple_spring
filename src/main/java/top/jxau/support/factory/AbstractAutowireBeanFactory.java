package top.jxau.support.factory;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.xml.internal.security.Init;
import org.apache.commons.lang3.StringUtils;
import top.jxau.support.bean.*;
import top.jxau.exceptions.BeansException;
import top.jxau.support.instantiation.InstantiationStrategy;
import top.jxau.support.instantiation.impl.CglibInstantiationStrategy;
import top.jxau.support.processor.BeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author plutohh
 */
public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    private Map<String, Object> semiBeanMap = new HashMap<>();

    @Override
    protected <T> T createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            fillPropertyValues(beanName, bean, beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed: ", e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        if(beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
        return (T) bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(!beanDefinition.isSingleton()) {
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(bean instanceof Aware) {
            if(bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware)bean).setBeanFactory(this);
            }
            if(bean instanceof BeanNameAware) {
                ((BeanNameAware)bean).setBeanName(beanName);
            }
            if(bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware)bean).setBeanClassLoader(getBeanClassLoader());
            }
        }
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(wrappedBean instanceof InitializingBean) {
            ((InitializingBean)(wrappedBean)).afterPropertiesSet();
        }

        String initMethodName = beanDefinition.getInitMethodName();
        if(StringUtils.isNotBlank(initMethodName)) {
            Method method = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(method == null) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(wrappedBean);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void fillPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if(propertyValues == null || propertyValues.getPropertyValues() == null || propertyValues.getPropertyValues().length < 1) {
                return;
            }
            for(PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String key = propertyValue.getKey();
                Object value = propertyValue.getValue();

                if(value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    String circleBeanName = beanReference.getBeanName();
                    // 防止循环依赖
                    value = semiBeanMap.get(circleBeanName);
                    if(value == null) {
                        semiBeanMap.put(circleBeanName, createBeanInstance(beanReference.getBeanName(), getBeanDefinition(beanReference.getBeanName()), null));
                        value = getBean(circleBeanName);
                    }
                }

                BeanUtil.setFieldValue(bean, key, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    private Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws NoSuchMethodException {

        // 存在中间对象直接返回
        if (semiBeanMap.containsKey(beanName)) {
            return semiBeanMap.get(beanName);
        }
        Constructor constructorToUse = null;

        if(args != null && args.length > 0) {
            Class clazz = beanDefinition.getBeanClass();
            Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
            for(Constructor constructor : declaredConstructors) {
                if(argsMatchConstructor(constructor, args)) {
                    constructorToUse = constructor;
                    break;
                }
            }
        }
        // 缓存中间对象
        Object instantiate = instantiationStrategy.instantiate(beanName, beanDefinition, constructorToUse, args);
        if(!semiBeanMap.containsKey(beanName)) {
            semiBeanMap.put(beanName, instantiate);
        }
        return instantiate;
    }

    private boolean argsMatchConstructor(Constructor constructor, Object[] args) {

        int parameterCount = constructor.getParameterCount();
        if(args.length != parameterCount) {
            return false;
        }
        Class[] parameterTypes = constructor.getParameterTypes();
        for(int i = 0; i < parameterTypes.length; i++) {
            if(parameterTypes[i] != args[i].getClass()) {
                return false;
            }
        }
        return true;
    }
}
