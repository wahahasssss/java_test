package com.hdu.mutildatasource.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:25
 */

@Component
public interface DB1Mapper {

    @Select("SELECT id FROM lupin_proxy_agent limit 1")
    int getId();
}
