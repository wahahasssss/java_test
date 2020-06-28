package com.hdu.SecondFSM;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:52
 */
public interface FsmState {
    void switchProxy();

    void beforeAction();

    void afterAction();


    int getStateCode();
}
