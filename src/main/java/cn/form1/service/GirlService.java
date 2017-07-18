package cn.form1.service;

import cn.form1.domain.Girl;
import cn.form1.domain.Result;
import cn.form1.enums.ResultEnum;
import cn.form1.exception.SpringGirlException;
import cn.form1.repository.GirlRepository;
import cn.form1.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试事务
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional //表示事务，其中save全部成功或全部失败
    public List<Girl> insertAll(){
        List<Girl> list = new ArrayList<Girl>();
        Girl girl1 = new Girl();
        girl1.setCupSize("C");
        girl1.setAge(26);
        list.add(girlRepository.save(girl1));

        Girl girl2 = new Girl();
        girl2.setCupSize("A");
        girl2.setAge(19);
        list.add(girlRepository.save(girl2));
        return list;
    }

    /*
    * 根据ID查询对应信息
    * */
    public Girl getGirlOne(Integer id) throws Exception{
        Girl girl = girlRepository.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new SpringGirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new SpringGirlException(ResultEnum.MIDDLE_SCHOOL);
        }
        return girl;
    }

}
