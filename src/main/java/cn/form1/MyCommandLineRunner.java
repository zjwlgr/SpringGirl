package cn.form1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 实现CommandLineRunner接口后
 * Spring boot在初始化全部完成后调用该类中的run方法
 * 需要纳入到Spring容器的管理中
 * 可以实现多个这种接口类
 * 获取的参数为原始参数，没有做过任何处理
 */
@Order(1) //如果有多个，可以使用@Order注解进行排序，数字越小，越先执行
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    /*
    * 必须实现接口中的 run 方法
    * */
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("=========CommandLineRunner--应用已经成功的启动=========" + Arrays.asList(strings));
        //Arrays.asList(strings) 为获取入口中指定的原始参数
    }
}
