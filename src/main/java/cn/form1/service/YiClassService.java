package cn.form1.service;


import cn.form1.domain.YiClass;
import cn.form1.mapper.YiClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 对yi_class表的操作
 */
@Service
public class YiClassService {

    @Autowired
    private YiClassMapper yiClassMapper;

    public YiClass selectByPrimaryKey(Integer id){
        YiClass yiClass = yiClassMapper.selectByPrimaryKey(id);
        return yiClass;
    }
    // could not aotuwired no beans of yiClassMapper type found
}
