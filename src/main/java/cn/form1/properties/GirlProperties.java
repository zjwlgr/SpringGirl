package cn.form1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 有时候有这样子的情景，我们想把配置文件的信息，读取并自动封装成实体类，
 * 这样子，我们在代码里面使用就轻松方便多了，这时候，我们就可以使用 @ConfigurationProperties，
 * 它可以把同类的配置信息自动封装成实体类
 */

@Component //把普通pojo实例化到spring容器中
@ConfigurationProperties(prefix = "myconfig") //获取配置文件中myconfig项的配置
public class GirlProperties {

    private Integer cookietime;

    private String userhomeurl;

    private Integer startweb;

    public Integer getCookietime() {
        return cookietime;
    }

    public void setCookietime(Integer cookietime) {
        this.cookietime = cookietime;
    }

    public String getUserhomeurl() {
        return userhomeurl;
    }

    public void setUserhomeurl(String userhomeurl) {
        this.userhomeurl = userhomeurl;
    }

    public Integer getStartweb() {
        return startweb;
    }

    public void setStartweb(Integer startweb) {
        this.startweb = startweb;
    }
}
