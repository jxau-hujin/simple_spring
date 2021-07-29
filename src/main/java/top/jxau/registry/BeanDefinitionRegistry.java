package top.jxau.registry;

import top.jxau.BeanDefinition;

/**
 * @author plutohh
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
