package com.hdu.dao;

import com.hdu.annation.LocalDatasource;
import com.hdu.model.po.TestTablePo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:30 PM
 */
@LocalDatasource
public interface LocalTestDao {


    @Select("SELECT * FROM test_tbl")
    List<TestTablePo> findAll();

    @Insert("INSERT INTO test_tbl (name, age, password) VALUES (#{info.name}, #{info.age}, #{info.password})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "info.id")
    int save(@Param("info") TestTablePo info);

    @Select("SELECT * FROM test_tbl WHERE id = #{id}")
    TestTablePo findOne(@Param("id") Long id);
}
