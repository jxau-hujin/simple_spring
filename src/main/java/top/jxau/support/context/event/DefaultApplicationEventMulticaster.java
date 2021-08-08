package top.jxau.support.context.event;

import top.jxau.support.context.ApplicationEvent;
import top.jxau.support.context.ApplicationListener;
import top.jxau.support.factory.BeanFactory;

/**
 * @author plutohh
 */
public class DefaultApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public DefaultApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }


    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
