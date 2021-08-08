package top.jxau;

import top.jxau.support.context.ApplicationListener;
import top.jxau.support.context.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("refreshed: " + this.getClass().getName());
    }
}
