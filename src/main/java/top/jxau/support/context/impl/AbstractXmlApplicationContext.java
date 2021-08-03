package top.jxau.support.context.impl;

import top.jxau.support.factory.impl.DefaultListableBeanFactory;
import top.jxau.support.xml.XmlBeanDefinitionReader;

/**
 * @author plutohh
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(configLocations != null) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取配置路径
     * @return
     */
    protected abstract String[] getConfigLocations();

}
