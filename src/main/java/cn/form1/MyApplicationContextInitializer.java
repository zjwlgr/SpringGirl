package cn.form1;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Spring boot在初始化之前调用该类中的initialize方法，需要实现ApplicationContextInitializer接口
 * 不需要纳入Spring容器管理中
 */
//打包war后无法使用
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>
{
    /*
    * 必须实现接口中的 initialize 方法
    * */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("应用启动前调用-===bean count : " + applicationContext.getBeanDefinitionCount());
    }
}

