package top.jxau.support.factory;

import top.jxau.exceptions.BeansException;
import top.jxau.registry.SingletonBeanRegistry;
import top.jxau.support.processor.BeanPostProcessor;

/**
 * @author plutohh
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加 BeanPostProcessor
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 提前实例化单例
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
