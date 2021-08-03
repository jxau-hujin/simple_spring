package top.jxau.support.factory;


import top.jxau.support.bean.BeanDefinition;
import top.jxau.registry.impl.DefaultSingletonBeanRegistry;
import top.jxau.support.processor.BeanPostProcessor;
import top.jxau.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author plutohh
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public <T> T getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName) {
        return doGetBean(beanName, null);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBean(beanName);
    }

    protected <T> T doGetBean(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        if(bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    /**
     * 根据 BeanName 获取 BeanDefinition
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 创建 Bean 对象
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     */
    protected abstract <T> T createBean(String beanName, BeanDefinition beanDefinition, Object[] args);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessorList;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
