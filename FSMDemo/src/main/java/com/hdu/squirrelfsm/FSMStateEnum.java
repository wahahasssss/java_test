package com.hdu.squirrelfsm;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:19
 */
public enum  FSMStateEnum {
    Normal("normal",1),
    Abnormal("abnormal",2),
    Setting("setting",3),
    Overload("overload",4),
    Banned("banned",5);
    private Integer index;

    private String description;

    FSMStateEnum(String description, int index) {
        this.index = index;
        this.description = description;
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


    public static int valueOf(FSMStateEnum state){
        switch (state){
            case Normal:
                return Normal.index;
            case Abnormal:
                return Abnormal.index;
            case Setting:
                return Setting.index;
            case Overload:
                return Overload.index;
            case Banned:
                return Banned.index;
            default:
                return Normal.index;
        }
    }
    public static String descriptionOf(FSMStateEnum state){
        switch (state){
            case Normal:
                return Normal.description;
            case Abnormal:
                return Abnormal.description;
            case Setting:
                return Setting.description;
            case Overload:
                return Overload.description;
            case Banned:
                return Banned.description;
            default:
                return Normal.description;
        }
    }
}
