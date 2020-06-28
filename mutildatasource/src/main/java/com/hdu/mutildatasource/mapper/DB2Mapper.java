package com.hdu.mutildatasource.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:26
 */
@Component
public interface DB2Mapper {

    @Select("SELECT * FROM lupinx_duty_detail limit 1")
    int getId();
}
