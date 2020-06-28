package com.hdu.action;

import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:38
 */
public enum StateEnum {
    NORMAL(1),
    ABNORMAL(2),
    BANNED(3),
    OVERLOAD(4),
    SETTING(5);
    private Integer code;

    StateEnum(Integer code) {
        this.code = code;
    }

    public static int valueOf(StateEnum value){
        return value.code;
    }
}
