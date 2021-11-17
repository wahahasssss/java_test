package com.hdu.action.impl;

import com.hdu.ContextBase;
import com.hdu.State;
import com.hdu.action.DecisionAction;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:16
 */
public class NormalDecisionAction implements DecisionAction {
    public int doDecisionAction(ContextBase context) {
        if (isSatisfied(context)) {
            //TODO do something
            context.getContextData().setCode(1);
            return context.getContextData().getCode();
        }
        return context.getContextData().getCode();
    }

    /**
     * 是否满足执行条件
     *
     * @param context
     * @return
     */
    public boolean isSatisfied(ContextBase context) {
        if (context.getContextData().getCode() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 状态改变之前
     *
     * @param context
     */
    public void beforeStateChange(ContextBase context) {
        System.out.println(String.format("the state is %d", context.getContextData().getCode()));
    }

    /**
     * 状态改变之后
     *
     * @param context
     */
    public void afterStateChange(ContextBase context) {

    }
}
