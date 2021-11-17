package com.hdu.clean_code.chapter2.factory;

import com.hdu.clean_code.chapter2.EmployeeType;
import com.hdu.clean_code.chapter2.exception.InvalidEmployeeType;

/**
 * @Author: ssf
 * @Date: 2020/3/30 2:16 下午
 */
public class EmployeeFactoryImpl implements EmployFactory {
    public Employee makeEmployee(EmployeeType type) throws InvalidEmployeeType {
        switch (type) {
            case COMMISSIONED:
                return new CommissionedEmployee();
            case HOURLY:
                return new HourlyEmployee();
            case SALARIED:
                return new SalariedEmployee();
            default:
                throw new InvalidEmployeeType();
        }
    }
}
