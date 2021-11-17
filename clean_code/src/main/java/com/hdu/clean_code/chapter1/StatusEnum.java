package com.hdu.clean_code.chapter1;

/**
 * @Author: ssf
 * @Date: 2020/3/26 2:14 下午
 */
public enum StatusEnum {
    STATUS_VALUE(1, "测试");
    private Integer index;
    private String desc;

    StatusEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }
}
