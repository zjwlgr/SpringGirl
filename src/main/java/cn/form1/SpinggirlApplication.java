package cn.form1;



import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.PropertySource;




@SpringBootApplication//(exclude = ErrorMvcAutoConfiguration.class) //exclude后不会在去把这个类纳入容器中管理
@PropertySource("classpath:jdbc.properties") //可加载多个配置文件
@MapperScan("cn.form1.mapper") //指定mybatis的Mapper的包路径

public class SpinggirlApplication/* extends SpringBootServletInitializer*/ {


    /**
     * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
     * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
     */
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpinggirlApplication.class);
    }
*/




	public static void main(String[] args) {

        SpringApplication.run(SpinggirlApplication.class, args);






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
        //app.addInitializers(new MyApplicationContextInitializer());



        /*   以下，一般不这样用，，暂废弃*/
        //创建Context上下文 并 运行应用
        //ConfigurableApplicationContext context = app.run(args);

        //发布一个事件，事件不一定在入口处发布..
        //context.publishEvent(new MyApplicationEvent(new Object()));
	}





	/*
	* 以下方法已废弃，使了单独ErrorpageConfig的类
	* */

    /*@Bean
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
    }*/
}
