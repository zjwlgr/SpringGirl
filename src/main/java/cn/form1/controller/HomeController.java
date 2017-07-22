package cn.form1.controller;

import cn.form1.event.MyApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试 Thymeleaf
 */
@Controller
public class HomeController {

    //发布事件驱动用的类
    @Autowired
    ApplicationContext applicationContext;


    /*
    * thymeleaf模板测试
    * */
    @GetMapping(value = "/home")
    public String home(ModelMap model){
        model.addAttribute("userphone", "13141437817");
        return "home";
    }

    /*
    * 自定义发布事 件
    * */
    @GetMapping(value = "/event")
    @ResponseBody
    public String myPublishEvent(){
        applicationContext.publishEvent(new MyApplicationEvent(new Object()));
        return "事件发布";
    }

    /*
    * 方法可接收HttpServletRequest对象
    * */
    @GetMapping(value = "/ip")
    @ResponseBody
    public String ip(HttpServletRequest httpServletRequest){
        return "IP: " + httpServletRequest.getRemoteAddr();
    }

    /*
    * 测试抛出异常
    * */
    @GetMapping(value = "/excp")
    public String excp() throws Exception{
        throw new Exception("有页面不可以爱得处理");
    }

    @GetMapping(value = "/wu")
    public String wu() throws ClassNotFoundException{
        throw new ClassNotFoundException("sdf500");
    }


}
