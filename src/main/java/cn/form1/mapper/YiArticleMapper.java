package cn.form1.mapper;

import cn.form1.domain.YiArticle;

public interface YiArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YiArticle record);

    int insertSelective(YiArticle record);

    YiArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YiArticle record);

    int updateByPrimaryKeyWithBLOBs(YiArticle record);

    int updateByPrimaryKey(YiArticle record);
}