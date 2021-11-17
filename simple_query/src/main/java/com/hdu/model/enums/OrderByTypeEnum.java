package com.hdu.model.enums;

public enum OrderByTypeEnum {
    ASC(0, "asc"),
    DESC(1, "desc");

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

    OrderByTypeEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }

}