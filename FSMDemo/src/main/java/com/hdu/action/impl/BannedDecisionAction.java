package com.hdu.action.impl;

import com.hdu.ContextBase;
import com.hdu.action.DecisionAction;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:44
 */
public class BannedDecisionAction implements DecisionAction{
    public int doDecisionAction(ContextBase context) {
        if (context.getContextData().getCode() == StateEnum.NORMAL.ordinal()){
            return StateEnum.NORMAL.ordinal();
        }
        //TODO  DO SOMETHING
        //切换代理
        return 0;
    }

    /**
     * 是否满足执行条件
     *
     * @param context
     * @return
     */
    public boolean isSatisfied(ContextBase context) {
        return false;
    }

    /**
     * 状态改变之前
     *
     * @param context
     */
    public void beforeStateChange(ContextBase context) {

    }

    /**
     * 状态改变之后
     *
     * @param context
     */
    public void afterStateChange(ContextBase context) {

    }
}
