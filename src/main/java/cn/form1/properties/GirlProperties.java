package cn.form1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置类
 */

@Component
@ConfigurationProperties(prefix = "myconfig")
//以上两个注解表示注入配置要加的注解
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
