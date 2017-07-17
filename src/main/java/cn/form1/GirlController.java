package cn.form1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangjian on 2017/7/17.
 */
@RestController
public class GirlController {
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
}
