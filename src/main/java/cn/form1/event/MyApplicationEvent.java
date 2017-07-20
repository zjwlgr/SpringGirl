package cn.form1.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 定义一个事件类
 */
public class MyApplicationEvent extends ApplicationEvent {

    public MyApplicationEvent(Object source) {
        super(source);
    }
}
