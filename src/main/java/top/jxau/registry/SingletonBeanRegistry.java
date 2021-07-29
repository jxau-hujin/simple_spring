package top.jxau.registry;

/**
 * @author plutohh
 */
public interface SingletonBeanRegistry {

    /**
     * 根据 beanName 获取单例 Bean 对象
     * @param beanName
     * @return
     */
    <T> T getSingleton(String beanName);
}
