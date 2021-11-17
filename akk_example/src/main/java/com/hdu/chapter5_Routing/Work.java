package com.hdu.chapter5_Routing;

import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/21
 * @Time 下午7:11
 */
public final class Work implements Serializable {
    private final String payload;

    public Work(String payload) {
        this.payload = payload;
    }
}
