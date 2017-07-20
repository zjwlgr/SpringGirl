package cn.form1;

import cn.form1.event.MyApplicationEvent;
import cn.form1.event.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpinggirlApplication {

	public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpinggirlApplication.class);

        /*添加事件监听器的4种方法
        * 1，手动在启动类中加入，如下以下方式
        * 2，在事件监听器类中加入 @Component 注解，将事件类纳入到Spring容器中管理
        * 3，通过配置项配置：context.listener.classes=cn.form1.event.MyApplicationListener
        * 4，使用方法中的 @EventListener 的注解 添加事件监听器，必须要纳入Spring容器中管理 @Component
        *  ---当用的第4种-- */
        //app.addListeners(new MyApplicationListener());

        /* 注册ApplicationContextInitializer后，Spring boot在初始化之前调用该类中的initialize方法
        1，创建实现注册ApplicationContextInitializer接口的类后，手动在以下注册
        2，创建实现注册ApplicationContextInitializer接口的类后，通过配置项配置:context.initializer.classes=类的全路径
        * */
        app.addInitializers(new MyApplicationContextInitializer());

        //创建Context上下文 并 运行应用
        ConfigurableApplicationContext context = app.run(args);

        //发布一个事件，事件不一定在入口处发布..
        context.publishEvent(new MyApplicationEvent(new Object()));
	}
}
