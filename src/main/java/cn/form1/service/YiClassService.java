package cn.form1.service;


import cn.form1.domain.YiClass;
import cn.form1.mapper.YiClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 基于XML的各种sql测试1
 */
@Service
public class YiClassService {

    @Autowired
    private YiClassMapper yiClassMapper;

    /*
    * 以ID为条件查询一条信息
    * */
    public YiClass selectByPrimaryKey(Integer id){
        YiClass yiClass = yiClassMapper.selectByPrimaryKey(id);
        return yiClass;
    }

    /*
    * 以fid与name条件查询一组信息
    * */
    public List<YiClass> selectList(Integer fid, String name){
        List<YiClass> list = yiClassMapper.selectList(fid, name);
        return list;
    }

}
