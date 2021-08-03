package top.jxau.support.bean;


/**
 * @author plutohh
 */
public interface BeanClassLoaderAware extends Aware{

    /**
     * 设置 Bean 类加载器
     * @param classLoader
     */
    void setBeanClassLoader(ClassLoader classLoader);

}


    