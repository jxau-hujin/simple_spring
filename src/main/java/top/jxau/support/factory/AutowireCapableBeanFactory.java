package top.jxau.support.factory;

/**
 * @author plutohh
 */
public interface AutowireCapableBeanFactory extends BeanFactory{

    /**
     * 对 Bean 对象初始化前处理
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    /**
     * 对 Bean 对象初始化后处理
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
}
