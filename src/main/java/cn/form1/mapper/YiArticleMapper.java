package cn.form1.mapper;

import cn.form1.domain.YiArticle;

import java.util.List;

public interface YiArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YiArticle record);

    int insertSelective(YiArticle record);

    YiArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YiArticle record);

    int updateByPrimaryKeyWithBLOBs(YiArticle record);

    int updateByPrimaryKey(YiArticle record);

    //like 查询
    List<YiArticle> listLike(String title);

    //多条件 + like 查询
    List<YiArticle> listWhereLike(String class_one, String class_two, String title);

    //id in (String) 传入字符串 + and class_one=String
    List<YiArticle> listIdIn(String[] ids, String one);
}