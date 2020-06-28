package com.hdu.action;

import com.hdu.ContextBase;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:24
 */
public interface Action {
    /**
     * 是否满足执行条件
     * @param context
     * @return
     */
    boolean isSatisfied(ContextBase context);

    /**
     * 状态改变之前
     * @param context
     */
    void beforeStateChange(ContextBase context);


    /**
     * 状态改变之后
     * @param context
     */
    void afterStateChange(ContextBase context);
}
