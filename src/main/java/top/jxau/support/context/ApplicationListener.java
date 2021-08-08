package top.jxau.support.context;

import java.util.EventListener;

/**
 * @author plutohh
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * onApplicationEvent
     * @param event
     */
    void onApplicationEvent(E event);
}
