package top.jxau;

import top.jxau.exceptions.BeansException;
import top.jxau.support.bean.ApplicationContextAware;
import top.jxau.support.bean.BeanClassLoaderAware;
import top.jxau.support.bean.BeanFactoryAware;
import top.jxau.support.context.ApplicationContext;
import top.jxau.support.factory.BeanFactory;

public class AwareBean implements BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;


    @Override
    public void setApplicationContextAware(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        return "AwareBean{" +
                "applicationContext=" + applicationContext +
                ", beanFactory=" + beanFactory +
                ", classLoader=" + classLoader +
                '}';
    }
}
