package com.hdu.clean_code.chapter2.factory;

import com.hdu.clean_code.chapter2.EmployeeType;
import com.hdu.clean_code.chapter2.exception.InvalidEmployeeType;

/**
 * @Author: ssf
 * @Date: 2020/3/30 2:13 下午
 */
public interface EmployFactory {
    Employee makeEmployee(EmployeeType type) throws InvalidEmployeeType;
}
