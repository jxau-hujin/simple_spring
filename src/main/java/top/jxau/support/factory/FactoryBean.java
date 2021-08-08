package top.jxau.support.factory;

/**
 * @author plutohh
 */
public interface FactoryBean<T>{

    /**
     * 获取对象
     * @return
     * @throws Exception
     */
    <T> T getObject() throws Exception;

    /**
     * 获取对象 Class
     * @return
     */
    Class<?> getObjectType();

    /**
     * 判断是否单例
     * @return
     */
    boolean isSingleton();
}
