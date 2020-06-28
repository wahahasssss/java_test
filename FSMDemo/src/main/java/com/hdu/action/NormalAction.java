package com.hdu.action;

import com.hdu.action.Action;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:29
 */
public interface NormalAction extends Action {
    /**
     * 状态变化的动作
     */
    void doAction();
}
