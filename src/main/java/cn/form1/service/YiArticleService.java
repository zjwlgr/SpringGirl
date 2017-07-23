package cn.form1.service;

import cn.form1.domain.YiArticle;
import cn.form1.mapper.YiArticleMapper;
import com.github.pagehelper.PageHelper;
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
    * 删除一个对象
    * */
    public int deleteByPrimaryKey(Integer id){
        return yiArticleMapper.deleteByPrimaryKey(id);
    }

    /*
    * 添加一个对象
    * */
    public int insert(YiArticle yiArticle){
        //return yiArticleMapper.insert(yiArticle);
        return yiArticleMapper.insertSelective(yiArticle);
    }

    /*
    * 编辑一个对象
    * */
    public int updateByPrimaryKeySelective(YiArticle yiArticle){
        return yiArticleMapper.updateByPrimaryKeySelective(yiArticle);
    }

    /*
    * 根据ID查询一个对象======自动生成的Mapper
    * */
    public YiArticle selectByPrimaryKey(Integer id){
        return yiArticleMapper.selectByPrimaryKey(id);
    }

    //======================================================================

    /*
    * 查询所有 + 分页
    * */
    public List<YiArticle> listall(){
        PageHelper.startPage(2,5);
        return yiArticleMapper.listall();
    }

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
    * id in (String) 传入数组或List测试  + and class_one=String
    * */
    public List<YiArticle> listIdIn(List<String> ids, String one){
        return yiArticleMapper.listIdIn(ids, one);
    }
}
