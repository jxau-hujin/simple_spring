package top.jxau.support.factory;


import top.jxau.BeanDefinition;
import top.jxau.BeanFactory;
import top.jxau.registry.impl.DefaultSingletonBeanRegistry;

/**
 * @author plutohh
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public <T> T getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName) {
        return doGetBean(beanName, null);
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


}