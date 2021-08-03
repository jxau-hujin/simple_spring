package top.jxau.support.factory;

/**
 * @author plutohh
 */
public interface BeanFactory {

    /**
     * 根据 beanName 获取 Bean
     * @param beanName
     * @return
     */
    <T> T getBean(String beanName);

    /**
     * 根据 args 获取 Bean
     * @param beanName
     * @param args
     * @param <T>
     * @return
     */
    <T> T getBean(String beanName, Object... args);

    /**
     * 根据 requiredType 获取 bean
     * @param beanName
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String beanName, Class<T> requiredType);
}
