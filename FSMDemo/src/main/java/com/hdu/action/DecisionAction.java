package com.hdu.action;

import com.hdu.ContextBase;
import com.hdu.State;
import com.hdu.action.Action;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:32
 */
public interface DecisionAction  extends Action {
    int doDecisionAction(ContextBase context);
}
