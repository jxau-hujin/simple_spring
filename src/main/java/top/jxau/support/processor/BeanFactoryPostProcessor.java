package top.jxau.support.processor;

import top.jxau.exceptions.BeansException;
import top.jxau.support.factory.ConfigurableListableBeanFactory;

/**
 * @author plutohh
 */
public interface BeanFactoryPostProcessor {

    /**
     * 实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
