package cn.form1.utils;

import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

/**
 * 文件上传类
 */
public class UploadUtil {

    //上传的文件大小限制 (0-不做限制) ，单位：字节
    private long maxSize = 0;

    //允许上传的文件后缀
    private String[] exts;

    //保存根路径，会在tomcat的webapps自动创建该文件夹
    private String rootPath = "uploadFile/";

    //保存路径，如 "userimage"
    private String savePath;

    //子目录创建方式，默认：年-月
    private  String subName = "yyyy-MM";

    public UploadUtil() {
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String[] getExts() {
        return exts;
    }

    public void setExts(String[] exts) {
        this.exts = exts;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     * 上传文件
     * @param request 当前请求的request
     */
    public String upload(HttpServletRequest request)  throws IllegalStateException, IOException {

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        //判断 request 是否有文件上传,即多部分请求,其实判断是否为（enctype="multipart/form-data" method="POST"）
        if(multipartResolver.isMultipart(request)){

            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;

            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();

            //记数器
            int num = 0;

            //为批量上传，所以如果有下一个信息，能循环输出
            while(iter.hasNext()){num++;

                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());

                //如果type=file中有文件上传
                if(!file.isEmpty()){

                    //取得当前上传文件的文件名称
                    String fileName = file.getOriginalFilename();

                    //获取文件大小，单位：字节
                    long fileSize = file.getSize();

                    //获取文件的后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));

                    //使用GUID重命名图片名称
                    fileName = UUID.randomUUID() + suffixName;

                    //获取当前项目的运行环境根目录,如：/C:/myJavaEEWorkSpace/SpringGirl/target/classes/
                    String projectPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();

                    //返回Tomcat的webapps根目录 (考虑到每次发布会覆盖war，文件最好存在war外面)
                    projectPath = projectPath + "../../../";

                    //设置文件存放子目录
                    SimpleDateFormat df = new SimpleDateFormat(this.getSubName());// 设置日期格式
                    String dateDir = df.format(new Date());// new Date()为获取当前系统时间

                    //文件最终保存全路径
                    String fileNamePath = projectPath + this.getRootPath() + dateDir + "/" + fileName;

                    //创建File对象
                    File localFile = new File(fileNamePath);

                    //检测是否存在目录，不存在则创建
                    if (!localFile.getParentFile().exists()) {
                        localFile.getParentFile().mkdirs();
                    }

                    //执行上传文件
                    file.transferTo(localFile);

                    return "/" + this.getRootPath() + dateDir + "/" + fileName;

                }

            }




        }


        return "";
    }
}
