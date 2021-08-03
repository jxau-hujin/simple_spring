package top.jxau.support.context.impl;

import top.jxau.exceptions.BeansException;
import top.jxau.support.bean.BeanDefinition;
import top.jxau.support.context.ConfigurableApplicationContext;
import top.jxau.support.core.io.loader.DefaultResourceLoader;
import top.jxau.support.factory.ConfigurableListableBeanFactory;
import top.jxau.support.processor.BeanFactoryPostProcessor;
import top.jxau.support.processor.BeanPostProcessor;

import java.util.Map;

/**
 * @author plutohh
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() {
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        invokeBeanFactoryPostProcessors(beanFactory);

        registerBeanPostProcessors(beanFactory);

        beanFactory.preInstantiateSingletons();
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 获取 BeanFactory
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    /**
     * 创建 BeanFactory 并加载 BeanDefinition
     */
    protected abstract void refreshBeanFactory();


    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        return getBeanFactory().getBeanDefinition(beanName);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName, requiredType);
    }
}
