package top.jxau.support.instantiation;

import top.jxau.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author plutohh
 */
public interface InstantiationStrategy {

    /**
     * 根据 BeanDefinition 实例化对象
     * @param beanDefinition
     * @return
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args);
}
