package cn.form1.controller;

import cn.form1.domain.Girl;
import cn.form1.repository.GirlRepository;
import cn.form1.service.GirlService;
import cn.form1.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GirlController {

    //在类中加载单一配置的值
    @Value("${webname}")
    private String webname;

    //引入注入配置的类
    @Autowired
    private GirlProperties girlProperties;

    //引入Repository接口类
    @Autowired
    private GirlRepository girlRepository;

    //引入Service数据处理类
    @Autowired
    private GirlService girlService;

    /*
    * 测试获取配置文件中的信息
    * */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config(){
        String str = this.webname + "_";
        str += this.girlProperties.getCookietime() + "_";
        str += this.girlProperties.getUserhomeurl() + "_";
        str += this.girlProperties.getStartweb() + "_";
        return str;
    }

    /*
    * 查询所有Girl表中的信息
    * */
    @GetMapping(value = "/girl")
    public List<Girl> listAll(){
        return girlRepository.findAll();
    }

    /*
    * 查询某一条信息
    * */
    @GetMapping(value = "/girl/{id}")
    public Girl listOne(@PathVariable(value = "id") Integer id){
        return girlRepository.findOne(id);
    }

    /*
    * 添加一条信息
    * */
    @PostMapping(value = "/girl")
    public Girl insertOne(@RequestParam(value = "cupsize",required = false,defaultValue = "F") String cupsize,
                          @RequestParam(value = "age",required = true) Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupsize);
        return girlRepository.save(girl);
    }

    /*
    * 修改一条信息
    * */
    @PutMapping(value = "/girl")
    public Girl updateOne(@RequestParam(value = "id",required = true) Integer id,
                          @RequestParam(value = "cupsize",required = false,defaultValue = "F") String cupsize,
                          @RequestParam(value = "age",required = true) Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupsize);
        return girlRepository.save(girl);
    }

    /*
    * 删除一条信息
    * */
    @DeleteMapping(value = "/girl")
    public Integer deleteOne(@RequestParam(value = "id",required = true) Integer id){
        girlRepository.delete(id);
        return 1;
    }

    /*
    * 测试事务
    * */
    @GetMapping(value = "/tran")
    public List<Girl> transactional(){
        return girlService.insertAll();
    }
}
