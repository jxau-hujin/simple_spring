package top.jxau.support.factory;

import com.sun.tools.internal.jxc.ap.Const;
import top.jxau.BeanDefinition;
import top.jxau.exceptions.BeansException;
import top.jxau.support.instantiation.InstantiationStrategy;
import top.jxau.support.instantiation.impl.CglibInstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * @author plutohh
 */
public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibInstantiationStrategy();

    @Override
    protected <T> T createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed: ", e);
        }
        addSingleton(beanName, bean);
        return (T) bean;
    }

    private Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws NoSuchMethodException {

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

        return instantiationStrategy.instantiate(beanName, beanDefinition, constructorToUse, args);
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
