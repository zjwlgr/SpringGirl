package cn.form1.controller;

import cn.form1.event.MyApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试 Thymeleaf
 */
@Controller
public class HomeController {

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping(value = "/home")
    public String home(ModelMap model){
        model.addAttribute("userphone", "13141437817");
        return "home";
    }

    /*
    * 自定义发布事件
    * */
    @GetMapping(value = "/event")
    @ResponseBody
    public String myPublishEvent(){
        applicationContext.publishEvent(new MyApplicationEvent(new Object()));
        return "事件发布";
    }

}
