package top.jxau.support.context;

/**
 * @author plutohh
 */
public interface ConfigurableApplicationContext extends ApplicationContext, ApplicationEventPublisher {

    /**
     * 刷新容器
     */
    void refresh();

    /**
     * 向虚拟机注册关闭钩子
     */
    void registerShutdownHook();

    /**
     * 应用上下文关闭
     */
    void close();
}
