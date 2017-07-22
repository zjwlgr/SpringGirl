package cn.form1.controller;

import cn.form1.domain.YiClass;
import cn.form1.service.YiClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Mybatis控制器
 */
@RestController
public class Classtcontorller {

    @Autowired
    private YiClassService yiClassService;


    @GetMapping(value = "/testmb")
    public YiClass testmb(@RequestParam(value = "id") Integer id){
        YiClass yiClass = yiClassService.selectByPrimaryKey(id);
        return yiClass;
    }


}
