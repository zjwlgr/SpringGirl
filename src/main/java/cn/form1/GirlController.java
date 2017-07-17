package cn.form1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GirlController {

    //在类中加载单一配置的值
    @Value("${webname}")
    private String webname;

    //引入注入配置的类
    @Autowired
    private Girlproperties girlproperties;

    /*
    * 测试获取配置文件中的信息
    * */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config(){
        String str = this.webname + "_";
        str += this.girlproperties.getCookietime() + "_";
        str += this.girlproperties.getUserhomeurl() + "_";
        str += this.girlproperties.getStartweb() + "_";
        return str;
    }
}
