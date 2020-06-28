package com.hdu.mutildatasource.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:04
 */
public class DynamicDataSourceConfig extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
