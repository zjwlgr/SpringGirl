package cn.form1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 实现ApplicationRunner接口后
 * Spring boot在初始化全部完成后调用该类中的run方法
 * 需要纳入到Spring容器的管理中
 * 可以实现多个这种接口类
 * 获取的参数 是对原始参数做了进一步的封装
 */
@Order(2)
@Component
public class MyApplicationRunner implements ApplicationRunner {

    /*
    * 必须实现接口中的 run 方法
    * */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("=========ApplicationRunner--应用已经成功的启动=========" + Arrays.asList(applicationArguments.getSourceArgs()));
        //Arrays.asList(applicationArguments.getSourceArgs()) 获取的参数 是对原始参数做了进一步的封装
    }
}
