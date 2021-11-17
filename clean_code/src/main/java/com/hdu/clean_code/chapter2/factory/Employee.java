package com.hdu.clean_code.chapter2.factory;

import com.hdu.clean_code.chapter2.Money;

/**
 * @Author: ssf
 * @Date: 2020/3/30 2:11 下午
 */
public abstract class Employee {
    public abstract boolean isPayday();

    public abstract Money calculatePay();

    public abstract void deliverPay(Money pay);
}
