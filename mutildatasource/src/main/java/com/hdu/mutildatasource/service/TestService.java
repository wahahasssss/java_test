package com.hdu.mutildatasource.service;


import com.hdu.mutildatasource.datasource.DatabaseContextHolder;
import com.hdu.mutildatasource.constant.DatabaseType;
import com.hdu.mutildatasource.mapper.DB1Mapper;
import com.hdu.mutildatasource.mapper.DB2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:27
 */
@Service
public class TestService {
    @Autowired
    private DB1Mapper db1Mapper;
    @Autowired
    private DB2Mapper db2Mapper;


    public String test1(){
        DatabaseContextHolder.setDataBaseType(DatabaseType.db1);
        return String.valueOf(db1Mapper.getId());
    }

    public String test2(){
        DatabaseContextHolder.setDataBaseType(DatabaseType.db2);
        return String.valueOf(db2Mapper.getId());
    }
}
