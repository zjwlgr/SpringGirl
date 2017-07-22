package cn.form1.controller;

import cn.form1.domain.Classt;
import cn.form1.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 测试Mybatis控制器
 */
@RestController
public class Classtcontorller {

    /*@Autowired
    private ClassService classService;

    @GetMapping(value = "/testmybatis/{fid}")
    public List<Classt> testmybatis(@PathVariable(value = "fid") Integer fid){
        List<Classt> classt = classService.classlistid(fid);
        return classt;
    }

    @GetMapping(value = "/test")
    public List<Classt> testname(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "fid") Integer fid){
        return classService.classlistname(name,fid);
    }*/

}
