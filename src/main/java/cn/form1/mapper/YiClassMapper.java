package cn.form1.mapper;

import cn.form1.domain.YiClass;

import java.util.List;

public interface YiClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YiClass record);

    int insertSelective(YiClass record);

    YiClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YiClass record);

    int updateByPrimaryKey(YiClass record);

    List<YiClass> selectList(Integer fid, String name);
}