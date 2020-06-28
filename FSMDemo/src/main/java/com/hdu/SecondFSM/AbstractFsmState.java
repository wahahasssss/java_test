package com.hdu.SecondFSM;

import com.hdu.SecondMachine;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午4:32
 */
public abstract class AbstractFsmState implements FsmState{

    protected SecondMachine machine;
    public AbstractFsmState(SecondMachine machine) {
        this.machine = machine;
    }

    public abstract void switchProxy();


    public void beforeAction() {
        System.out.println(String.format("the before state is %d",getStateCode()));
    }

    public void afterAction() {
        System.out.println(String.format("the after state is %d",getStateCode()));
    }

    public abstract int getStateCode();
}
