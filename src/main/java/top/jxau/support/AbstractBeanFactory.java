package top.jxau.support;


import top.jxau.BeanDefinition;
import top.jxau.BeanFactory;
import top.jxau.registry.impl.DefaultSingletonBeanRegistry;

/**
 * @author plutohh
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public <T> T getBean(String beanName) {
        T bean = getSingleton(beanName);
        if(bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * 根据 BeanName 获取 BeanDefinition
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    /**
     * 创建 Bean 对象
     * @param beanName
     * @param beanDefinition
     * @return
     */
    protected abstract <T> T createBean(String beanName, BeanDefinition beanDefinition);


}
