package cn.form1.service;

import cn.form1.domain.Girl;
import cn.form1.repository.GirlRepository;
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

}
