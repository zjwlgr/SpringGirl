package cn.form1.controller;

import cn.form1.domain.YiArticle;
import cn.form1.service.YiArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 基于XML的各种sql测试
 */
@RestController
public class TestXmlSqlController {

    @Autowired
    private YiArticleService yiArticleService;

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
    *  id in (String) 传入数组+ and class_one=String 使用 PathVariable
    * */
    @GetMapping(value = "/idin_{ids}_{one}")
    public List<YiArticle> listIdIn(@PathVariable(value = "ids") String ids,
                                    @PathVariable(value = "one") String one){
        String[] arr = ids.split(",");
        /*for(String c : arr){
            System.out.println(c);
        }*/
        return yiArticleService.listIdIn(arr, one);
    }

    /*
    *  id in (List) 传入List
    * */

}
