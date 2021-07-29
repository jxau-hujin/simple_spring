package top.jxau.registry;

import top.jxau.support.bean.BeanDefinition;

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

    /**
     * 判断是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
