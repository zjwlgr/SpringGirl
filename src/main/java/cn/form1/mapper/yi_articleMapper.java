package cn.form1.mapper;

import cn.form1.domain.yi_article;

/*
* 只是测试insert当前时间或时间戳入库
* */

public interface yi_articleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(yi_article record);

    int insertSelective(yi_article record);

    yi_article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(yi_article record);

    int updateByPrimaryKeyWithBLOBs(yi_article record);

    int updateByPrimaryKey(yi_article record);
}