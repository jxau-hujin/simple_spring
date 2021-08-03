package top.jxau.support.processor;

import top.jxau.exceptions.BeansException;
import top.jxau.support.bean.*;
import top.jxau.support.context.ApplicationContext;

/**
 * @author plutohh
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContextAware(applicationContext);
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
