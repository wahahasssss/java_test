package com.hdu;

import com.hdu.action.DecisionAction;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:11
 */
public class TestStateMachine implements State {
    private int state;


    public TestStateMachine(int state) {
        this.state = state;
    }

    public int getCode() {
        return 0;
    }

    public void setCode(int code) {
        state = code;
    }
}
