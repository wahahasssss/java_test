package com.hdu.model.enums;

public enum MessageCodeEnum {
    SUCCESS(200, "success");
    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    MessageCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
