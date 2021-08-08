package top.jxau;

import top.jxau.support.context.ApplicationListener;
import top.jxau.support.context.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override

    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("closed：" + this.getClass().getName());
    }
}
