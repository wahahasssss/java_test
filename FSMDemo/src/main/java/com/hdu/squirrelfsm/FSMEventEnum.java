package com.hdu.squirrelfsm;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:04
 */
public enum FSMEventEnum {
    ToNormal("event Normal",1),
    ToAbnormal("event abnormal",2),
    ToSetting("event setting",3),
    ToOverload("event overload",4),
    ToBanned("event banned",5);
    private String description;
    private Integer index;

    FSMEventEnum(String description, Integer index) {
        this.description = description;
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public static int valueOf(FSMEventEnum event){
        switch (event){
            case ToNormal:
                return ToNormal.index;
            case ToAbnormal:
                return ToAbnormal.index;
            case ToBanned:
                return ToBanned.index;
            case ToOverload:
                return ToOverload.index;
            case ToSetting:
                return ToSetting.index;
                default:
                    return 1;
        }
    }

    public static String description(FSMEventEnum event){
        switch (event){
            case ToNormal:
                return ToNormal.description;
            case ToAbnormal:
                return ToAbnormal.description;
            case ToBanned:
                return ToBanned.description;
            case ToOverload:
                return ToOverload.description;
            case ToSetting:
                return ToSetting.description;
            default:
                return ToNormal.description;
        }
    }

}
