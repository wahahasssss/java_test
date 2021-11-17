package com.hdu.mutildatasource.datasource;

import com.hdu.mutildatasource.constant.DatabaseType;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/2
 * @Time 下午2:02
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDataBaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }
}
