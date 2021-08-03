package top.jxau.support.context;

/**
 * @author plutohh
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh();
}
