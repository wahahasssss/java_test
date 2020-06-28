package com.hdu.dao;

import com.hdu.annation.DevDatasource;
import com.hdu.model.po.TestDevPo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:42 PM
 */
@DevDatasource
public interface DevTestDao {

    @Select("SELECT * FROM students")
    List<TestDevPo> findAll();
}
