package cn.form1.service;

import cn.form1.domain.YiArticle;
import cn.form1.mapper.YiArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基于XML的各种sql测试2
 */
@Service
public class YiArticleService {

    @Autowired
    private YiArticleMapper yiArticleMapper;

    /*
    * like查询
    * */
    public List<YiArticle> listLike(String title){
        return yiArticleMapper.listLike(title);
    }

    /*
    * 多条件 + like 查询
    * */
    public List<YiArticle> listWhereLike(String class_one, String class_two, String title){
        return yiArticleMapper.listWhereLike(class_one,class_two,title);
    }

    /*
    * id in (String) 传入字符串 + and class_one=String
    * */
    public List<YiArticle> listIdIn(String[] ids, String one){
        return yiArticleMapper.listIdIn(ids, one);
    }
}
