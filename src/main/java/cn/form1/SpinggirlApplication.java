package cn.form1;

import cn.form1.event.MyApplicationEvent;
import cn.form1.event.MyApplicationListener;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;

import javax.annotation.ManagedBean;


@SpringBootApplication()//(exclude = ErrorMvcAutoConfiguration.class) //exclude后不会在去把这个类纳入容器中管理
@PropertySource("classpath:jdbc.properties") //可加载多个配置文件
@MapperScan("cn.form1.mapper") //指定mybatis的Mapper的包路径
public class SpinggirlApplication {

	public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpinggirlApplication.class);

        //设置控制台输入的Banner
        //app.setBannerMode(Banner.Mode.OFF);

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
        //context.publishEvent(new MyApplicationEvent(new Object()));
	}



    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
