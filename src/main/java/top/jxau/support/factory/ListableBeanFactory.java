package top.jxau.support.factory;

import top.jxau.exceptions.BeansException;
import top.jxau.support.bean.BeanDefinition;

import java.util.Map;

/**
 * @author plutohh
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 根据类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     */
    <T> Map<String, T> getBeanOfType(Class<T> type);

    /**
     * 获取注册 Map 中所有 BeanDefinition 名称
     * @return
     */
    String[] getBeanDefinitionNames();

    /**
     * 根据 beanName 获取 beanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
