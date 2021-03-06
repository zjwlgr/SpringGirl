package cn.form1.controller;

import cn.form1.domain.Girl;
import cn.form1.domain.Result;
import cn.form1.repository.GirlRepository;
import cn.form1.service.GirlService;
import cn.form1.properties.GirlProperties;
import cn.form1.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //Logger是Spring自带的一个日志框架
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

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
    public Result<Girl> listAll(){
        logger.info("girlList");
        return ResultUtil.success(girlRepository.findAll());
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
    /*public Girl insertOne(@RequestParam(value = "cupsize",required = false,defaultValue = "F") String cupsize,
                          @RequestParam(value = "age",required = true) Integer age){*/
    public Result insertOne(@Valid Girl girl, BindingResult bindingResult){
        //把多个单条参数换为对象 Girl girl
        //@Valid  表要验证他后面的对象
        //验证结果会返回到 bindingResult 这个对象中
        if(bindingResult.hasErrors()){//如果有错误
            // bindingResult.getFieldError().getDefaultMessage() //该行表示获取错误信息
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }

        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());

        return ResultUtil.success(girlRepository.save(girl));
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

    /*
    * 检测女生年龄方法，测试统一异常处理
    * */
    @GetMapping(value = "/girl/getage/{id}")
    public Result getAge(@PathVariable(value = "id") Integer id) throws Exception{
        Girl girl = girlService.getGirlOne(id);
        return ResultUtil.success(girl);
    }

}
