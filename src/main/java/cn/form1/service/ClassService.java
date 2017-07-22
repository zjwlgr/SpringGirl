package cn.form1.service;

import cn.form1.domain.Classt;
import cn.form1.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 对yi_class表的操作
 */
@Service
public class ClassService {

    @Autowired
    private ClassMapper classMapper;

    /*
    * 测试 Mybatis 的selecct
    * */
    /*public List<Classt> classlist(Integer fid, String name){
        return classMapper.selectfid(fid,name);
    }*/

    /*
    * 测试 Mybatis 的selecct
    * */
    public List<Classt> classlistid(Integer id){
        return classMapper.selectfid(id);
    }

    /*
        * 测试 Mybatis 的selecct
        * */
    public List<Classt> classlistname(String name,Integer fid){
        return classMapper.selectfname(name,fid);
    }

}
