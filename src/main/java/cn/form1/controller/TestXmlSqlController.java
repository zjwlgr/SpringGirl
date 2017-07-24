package cn.form1.controller;

import cn.form1.domain.YiArticle;
import cn.form1.service.YiArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于XML的各种sql测试
 */
@RestController
public class TestXmlSqlController {

    @Autowired
    private YiArticleService yiArticleService;

    /*
    * 删除一个对象
    * */
    @GetMapping(value = "/delete")
    public int delete(@RequestParam(value = "id") Integer id){
        return yiArticleService.deleteByPrimaryKey(id);
        //删除成功返回1，失败返回0
    }

    /*
    * 添加一个对象 并返回该对象，会包含该对象的自增ID
    * */
    @PostMapping(value = "/insert")
    public YiArticle insert(YiArticle yiArticle){
        int insert = yiArticleService.insert(yiArticle);
        //删除成功返回1，失败返回0
        return yiArticle;
    }

    /*
    * 编辑一个对象
    * */
    @PostMapping(value = "/update")
    public int update(YiArticle yiArticle){
        return yiArticleService.updateByPrimaryKeySelective(yiArticle);
        //删除成功返回1，失败返回0
    }

    /*
    * 根据ID查询一个对象======自动生成的Mapper
    * */
    @GetMapping(value = "/article")
    public YiArticle article(@RequestParam(value ="id") Integer id){
        return yiArticleService.selectByPrimaryKey(id);
    }

    //======================================================================

    /*
    * 查询所有 + 分页
    * */
    @GetMapping(value = "/listall")
    public ModelAndView listall(YiArticle yiArticle){
        ModelAndView result = new ModelAndView("pages");
        List<YiArticle> yiArticles = yiArticleService.listall(yiArticle);
        result.addObject("pageInfo", new PageInfo<YiArticle>(yiArticles));//实例化一个PageInfo对象
        result.addObject("queryParam", yiArticle);//主要获取?后面参数
        result.addObject("page", yiArticle.getPage());
        result.addObject("rows", yiArticle.getRows());
        return result;
    }

    /*
    * like 查询
    * */
    @GetMapping(value = "/like")
    public List<YiArticle> listLike(@RequestParam(value = "rong") String title){
        return yiArticleService.listLike(title);
    }

    /*
    * 多条件 + like 查询
    * */
    @GetMapping(value = "/wherelike")
    public List<YiArticle> listWhereLike(@RequestParam(value = "one", required = false) String one,
                                         @RequestParam(value = "two", required = false) String two,
                                         @RequestParam(value = "title", required = false) String title){
        return yiArticleService.listWhereLike(one,two,title);
    }

    /*
    *  id in (String) 传入数组或List测试   + and class_one=String 使用 PathVariable
    * */
    @GetMapping(value = "/idin_{ids}_{one}")
    public List<YiArticle> listIdIn(@PathVariable(value = "ids") String ids,
                                    @PathVariable(value = "one") String one){
        String[] arr = ids.split(","); //传入数组测试成功
        List<String> str = new ArrayList<>(); //传入List测试成功
        for(String c : arr){
            //System.out.println(c);
            str.add(c);//把字符串加入到List中
        }
        for (String d : str){
            //System.out.println(d);
        }
        return yiArticleService.listIdIn(str, one);
    }

    /*
    * 多表查询，关联yi_class表
    * */
    @GetMapping(value = "/listJoin")
    public List<YiArticle> listJoin(@RequestParam(value = "two") String two){
        return yiArticleService.listJoin(two);
    }

    /*
    * 聚合查询之 Count
    * */
    @GetMapping(value = "/listCont")
    public Integer listCount(@RequestParam(value = "two_id") Integer two_id){
        return yiArticleService.listCount(two_id);
    }



}
