package com.hdu.netty.timer;

import java.util.Date;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/26
 * @Time 上午10:13
 */
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }


    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((getValue() - 2208988800L) * 1000L).toString();
    }
}

