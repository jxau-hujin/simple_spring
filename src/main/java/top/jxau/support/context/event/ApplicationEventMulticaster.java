package top.jxau.support.context.event;

import top.jxau.support.context.ApplicationEvent;
import top.jxau.support.context.ApplicationListener;

/**
 * @author plutohh
 */
public interface ApplicationEventMulticaster {

    /**
     * addApplicationListener
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * removeApplicationListener
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);


    /**
     * multicastEvent
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
