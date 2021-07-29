package top.jxau.support.factory.impl;

import top.jxau.support.bean.BeanDefinition;
import top.jxau.exceptions.BeansException;
import top.jxau.registry.BeanDefinitionRegistry;
import top.jxau.support.factory.AbstractAutowireBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author plutohh
 */
public class DefaultBeanFactory extends AbstractAutowireBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) {
            throw new BeansException("Not Found Bean Named: " + beanName);
        }
        return beanDefinition;
    }
}
