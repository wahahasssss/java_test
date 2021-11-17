package com.hdu.clean_code.chapter2;

import com.hdu.clean_code.chapter2.exception.InvalidEmployeeType;

/**
 * @Author: ssf
 * @Date: 2020/3/26 3:43 下午
 */
public class CalculatePayDemo {
    public Money calculatePay(Employee e) {
        switch (e.getType()) {
            case COMMISSIONED:
                return calculateCommissionedPay(e);
            case HOURLY:
                return calculateHourlyPay(e);
            case SALARIED:
                return calculateSalariedPay(e);
            default:
                throw new InvalidEmployeeType();
        }
    }


    public Money calculateCommissionedPay(Employee e) {
        //todo
        return null;
    }


    public Money calculateHourlyPay(Employee e) {
        return null;
    }

    public Money calculateSalariedPay(Employee e) {
        return null;
    }
}
