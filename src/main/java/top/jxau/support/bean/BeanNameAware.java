package top.jxau.support.bean;


/**
 * @author plutohh
 */
public interface BeanNameAware extends Aware {

    /**
     * 设置 BeanName
     * @param name
     */
    void setBeanName(String name);

}

