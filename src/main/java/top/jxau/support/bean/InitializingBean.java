package top.jxau.support.bean;

/**
 * @author plutohh
 */
public interface InitializingBean {

    /**
     * Bean 实例化后初始化方法
     */
    void afterPropertiesSet();
}
