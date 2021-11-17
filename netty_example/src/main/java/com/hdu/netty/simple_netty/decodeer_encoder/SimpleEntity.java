package com.hdu.netty.simple_netty.decodeer_encoder;

import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/21
 * @Time 下午5:52
 */
public class SimpleEntity implements Serializable {
    private String date;
    private String time;

    public SimpleEntity() {
    }

    public SimpleEntity(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
