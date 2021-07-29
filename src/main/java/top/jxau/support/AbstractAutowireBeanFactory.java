package top.jxau.support;

import top.jxau.BeanDefinition;
import top.jxau.exceptions.BeansException;

/**
 * @author plutohh
 */
public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory{

    @Override
    protected <T> T createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            // TODO 日志
            throw new BeansException("InstantiationException", e);
        } catch (IllegalAccessException e) {
            // TODO 日志
            throw new BeansException("IllegalAccessException", e);
        }
        addSingleton(beanName, bean);
        return (T) bean;
    }
}
