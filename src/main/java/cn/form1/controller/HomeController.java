package cn.form1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试 Thymeleaf
 */
@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public String home(ModelMap model){
        model.addAttribute("userphone", "13141437817");
        return "home";
    }

}
