package top.jxau;

import top.jxau.support.context.ApplicationContextEvent;

public class MsgEvent extends ApplicationContextEvent {

    private Long id;
    private String msg;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public MsgEvent(Object source, Long id, String msg) {
        super(source);
        this.id = id;
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }
}
