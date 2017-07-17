package cn.form1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GirlController {

    @GetMapping(value = "/")
    public String index(){

        return "index";
    }
}
