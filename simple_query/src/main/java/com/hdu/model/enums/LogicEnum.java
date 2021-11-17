package com.hdu.model.enums;

public enum LogicEnum {
    AND(0, "与"),
    OR(1, "或");

    private Integer index;
    private String desc;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    LogicEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }
}