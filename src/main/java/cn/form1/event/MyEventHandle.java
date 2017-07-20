package cn.form1.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用方法中的 @EventListener 的注解 添加事件监听器，必须要纳入Spring容器中管理 @Component
 */
@Component
public class MyEventHandle {

    @EventListener
    public void event(MyApplicationEvent event) {
        //TODO 实现自己要处理的一些业务逻辑
        System.out.println("===EventListener: 接收到的事件========" + event.getClass());
    }
}
