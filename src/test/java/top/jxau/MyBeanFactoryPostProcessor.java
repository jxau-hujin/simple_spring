package top.jxau;

import top.jxau.exceptions.BeansException;
import top.jxau.support.bean.BeanDefinition;
import top.jxau.support.bean.PropertyValue;
import top.jxau.support.bean.PropertyValues;
import top.jxau.support.factory.ConfigurableListableBeanFactory;
import top.jxau.support.processor.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("username", "plutohh"));
    }
}
