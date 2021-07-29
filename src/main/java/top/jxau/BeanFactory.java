package top.jxau;

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
}
