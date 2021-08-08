package top.jxau;

import top.jxau.support.context.ApplicationListener;

public class MsgEventListener implements ApplicationListener<MsgEvent> {

    @Override
    public void onApplicationEvent(MsgEvent event) {
        System.out.println("receiver: " + event.getSource() + " id: " + event.getId() + " msg: " + event.getMsg());
    }
}
