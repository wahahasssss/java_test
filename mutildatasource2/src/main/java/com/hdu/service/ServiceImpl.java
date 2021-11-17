package com.hdu.service;

import com.hdu.dao.DevTestDao;
import com.hdu.dao.LocalTestDao;
import com.hdu.model.po.TestTablePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:44 PM
 */
@Service
public class ServiceImpl implements IService {
    @Autowired
    private DevTestDao devTestDao;
    @Autowired
    private LocalTestDao localTestDao;

    @Override
    public void queryAllData() {
        localTestDao.findAll().forEach(s -> System.out.println(s));
        devTestDao.findAll().forEach(s -> System.out.println(s));
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Long transactionTest() {
        TestTablePo info = TestTablePo.builder()
                .age(12)
                .name("fsa")
                .password("3232ffa")
                .build();
        localTestDao.save(info);
        System.out.println(info);
        return info.getId();
    }

    @Transactional(propagation = Propagation.NEVER)
    public void readAfterCommit() {
        long id = transactionTest();
        TestTablePo t = localTestDao.findOne(id);
        if (t != null) {
            System.out.println(t);
        } else {
            System.out.println("null" + id);
        }
    }
}
