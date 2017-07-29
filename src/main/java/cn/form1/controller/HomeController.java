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

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    //文件上传相关代码
    @RequestMapping(value = "/upload_UP")
    @ResponseBody
    public String upload(@RequestParam(value = "test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }


        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("filename:" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("suffixName:" + suffixName);

        // 文件上传后的路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
        String dateDir = df.format(new Date());// new Date()为获取当前系统时间
        String filePath = "C://testssssssssssss//"+dateDir+"//";

        // 使用GUID重命名图片名称
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);

        //获取文件大小
        long filesize = dest.length();
        logger.info("filesize:" + filesize);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @RequestMapping(value = "/uploadjar")
    @ResponseBody
    public String uploadjar(HttpServletRequest request) throws IllegalStateException, IOException{


        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        /*multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(11);
        multipartResolver.setMaxInMemorySize(11);*/

        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();


            //批量上传是按表单顺序返回来的
            int i = 0;
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());

               //System.out.println("currentTimeMillis："+pre);

                if(!file.isEmpty()){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    System.out.println("getSize："+file.getSize());

                    // 获取文件的后缀名
                    String suffixName = myFileName.substring(myFileName.lastIndexOf("."));

                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        i++;
                        System.out.println("myFileName："+myFileName);
                        //重命名上传后的文件名
                        // 使用GUID重命名图片名称
                        myFileName = UUID.randomUUID() + suffixName;
                        String fileName = myFileName;



                        //定义上传路径
                        //      /C:/myJavaEEWorkSp...返回中前面有个斜杠，所以下面要去掉
                        String pathss = ClassUtils.getDefaultClassLoader().getResource("").getPath();
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
                        String dateDir = df.format(new Date());// new Date()为获取当前系统时间
                        pathss = pathss + "static/upload/"+dateDir+"/";

                        String path = pathss.substring(1) + fileName;



                        File localFile = new File(path);

                        // 检测是否存在目录
                        if (!localFile.getParentFile().exists()) {
                            localFile.getParentFile().mkdirs();
                        }

                        file.transferTo(localFile);
                        System.out.println("path==========="+i+"-"+path);
                    }
                }
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
                //System.out.println(finaltime - pre);



            }

        }else{
            return "请选择要上传的文件！";
        }
        return "success";
        //http://blog.csdn.net/a1314517love/article/details/24183273

    }


    /*
    * 测试上传类
    * */
    @RequestMapping(value = "/uploadclass")
    @ResponseBody
    public String uploadclass(HttpServletRequest request) throws IllegalStateException, IOException{

        UploadUtil uploadUtil = new UploadUtil();
        String str = uploadUtil.upload(request);

        return str;

    }


//TODO  验证码，加密解密方法，md5，















}
