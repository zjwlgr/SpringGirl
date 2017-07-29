package cn.form1.controller;

import cn.form1.event.MyApplicationEvent;
import cn.form1.utils.CookieUtil;
import cn.form1.utils.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    /*
    * 测试查找字符串，与字符串是否为空
    * */
    @GetMapping(value = "/isstr")
    @ResponseBody
    public String isstr(){
        String str = ".jpg*.png*.gif*.jpeg";
        String ext = ".jpeg";
        if(str.indexOf(ext) != -1){
            return "yes";
        }else{
            return "no";
        }
        /*if(str.isEmpty()){
            return "null";
        }else{
            return "yes";
        }*/


    }

    /*
    * ====================================上传文件测试
    * */
    @GetMapping(value = "/upload")
    public String upload(ModelMap model){

        //在这里测试获取项目运行路径
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        model.addAttribute("path",path.substring(1));
        //输出为：/E:/Program%20Files/Java/apache-tomcat-8.5.16/webapps/spinggirl-0.0.1-SNAPSHOT/WEB-INF/classes/ 这里在加upload/userimg/2939484/sdfsdf.jpg
        return "upload";
    }


    /*
    * 测试上文件传类
    * */
    @RequestMapping(value = "/uploadclass")
    @ResponseBody
    public String uploadclass(HttpServletRequest request) throws IllegalStateException, IOException{

        UploadUtil upload = new UploadUtil();
        upload.setMaxSize(50000);
        upload.setExts(".jpg|.png|.gif|.jpeg");
        upload.setSavePath("mydogimg/");
        if(upload.upload(request)){
            List list = upload.getFileNames();
            String str = (String) list.get(0);
            return str+"===";
        }else{
            return upload.getError();
        }
    }


//TODO  下载文件测试，验证码，加密解密方法，md5















}
