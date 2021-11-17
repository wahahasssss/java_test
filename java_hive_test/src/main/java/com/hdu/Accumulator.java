package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 下午4:25
 */
public class Accumulator {
    private Long sum = 0L;

    public Accumulator() {
    }

    public Long getSum() {
        return sum;
    }

    public Long add(Long a) {
        sum = sum + a;
        return sum;
    }

}
