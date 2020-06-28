package com.hdu.clean_code.chapter2.factory;

import com.hdu.clean_code.chapter2.Money;

/**
 * @Author: ssf
 * @Date: 2020/3/30 2:27 下午
 */
public class SalariedEmployee extends Employee{
    @Override
    public boolean isPayday() {
        return false;
    }

    @Override
    public Money calculatePay() {
        return null;
    }

    @Override
    public void deliverPay(Money pay) {

    }
}
