package cn.form1.event;

import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;

/**
 * 定义一个监听器
 * MyApplicationEvent 为要监听的类型
 */
//@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    /*
    * ApplicationListener 的抽象方法 会传入我们要监听的事件
    * */
    @Override
    public void onApplicationEvent(MyApplicationEvent event) {
        //TODO 实现自己要处理的一些业务逻辑
        System.out.println("========接收到的事件========" + event.getClass());
    }
}
