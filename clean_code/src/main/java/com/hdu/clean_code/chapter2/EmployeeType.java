package com.hdu.clean_code.chapter2;

/**
 * @Author: ssf
 * @Date: 2020/3/26 3:41 下午
 */
public enum EmployeeType {
    COMMISSIONED(1),
    HOURLY(2),
    SALARIED(3);
    private Integer index;

    EmployeeType(Integer index) {
        this.index = index;
    }
}
