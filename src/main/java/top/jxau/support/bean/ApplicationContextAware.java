package top.jxau.support.bean;

import top.jxau.support.context.ApplicationContext;

/**
 * @author plutohh
 */
public interface ApplicationContextAware {

    /**
     * 设置应用上下文
     * @param applicationContext
     */
    void setApplicationContextAware(ApplicationContext applicationContext);

}
