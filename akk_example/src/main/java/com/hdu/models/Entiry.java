package com.hdu.models;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/20
 * @Time 下午5:30
 */
public class Entiry {
    private String str;

    public Entiry(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Entiry{" +
                "str='" + str + '\'' +
                '}';
    }
}
