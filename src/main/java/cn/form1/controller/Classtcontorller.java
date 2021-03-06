package cn.form1.controller;

import cn.form1.domain.YiClass;
import cn.form1.service.YiClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试Mybatis控制器
 * 基于XML的各种sql测试1
 */
@RestController
public class Classtcontorller {

    @Autowired
    private YiClassService yiClassService;    //yi_class

    /*
    * 以ID为条件查询一条信息    yi_class
    * */
    @GetMapping(value = "/idone")
    public YiClass idone(@RequestParam(value = "id") Integer id) {
        YiClass yiClass = yiClassService.selectByPrimaryKey(id);
        return yiClass;
    }

    /*
    * 以fid与name条件查询一组信息    yi_class
    * */
    @GetMapping(value = "/listallc")
    public List<YiClass> listall(@RequestParam(value = "fid") Integer fid,
                                 @RequestParam(value = "name") String name) {
        List<YiClass> list = yiClassService.selectList(fid, name);
        return list;
    }

}
