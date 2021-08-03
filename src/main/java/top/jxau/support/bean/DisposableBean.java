package top.jxau.support.bean;

/**
 * @author plutohh
 */
public interface DisposableBean {

    /**
     * Bean 对象实例化销毁方法
     */
    void destroy() throws Exception;


}
