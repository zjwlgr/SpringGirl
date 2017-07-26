package cn.form1.controller;

import cn.form1.event.MyApplicationEvent;
import cn.form1.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

    /*
    * 测试session 设置session
    * */
    @GetMapping(value = "/setsession")
    public String setsession(ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("data", "form1.cn");
        model.addAttribute("val","sessionss");
        return "testsession";
    }

    /*
    * 测试session 获取session
    * */
    @GetMapping(value = "/getsession")
    public String getsession(ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        model.addAttribute("val",session.getAttribute("data"));
        return "testsession";
    }

    /*
        * 测试session 清除session
        * */
    @GetMapping(value = "/delsession")
    @ResponseBody
    public String delsession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("data");
        return "OK";
    }

    /*
    * 测试cookie 设置cookie
    * */
    @GetMapping(value = "/setcookie")
    public String setcookie(ModelMap model, HttpServletResponse response){
        CookieUtil.addCookie(response,"username","zjwlgr张",3600);
        model.addAttribute("val","cookie");
        return "testcookie";
    }

    /*
    * 测试cookie 读取cookie
    * */
    @GetMapping(value = "/getcookie")
    public String getcookie(ModelMap model, HttpServletRequest request){
        Cookie cookie = CookieUtil.getCookieByName(request,"username");
        model.addAttribute("val",cookie.getValue());
        return "testcookie";
    }

    /*
    * 测试cookie  删除cookie
    * */
    @GetMapping(value = "/delcookie")
    @ResponseBody
    public String getcookie(HttpServletRequest request, HttpServletResponse response){
        CookieUtil.delCookieAll(response, request);
        return "OK";
    }


}
