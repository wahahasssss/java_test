package com.hdu.squirrelfsm;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:28
 */
public class FSMContext {
    private FSMStateEnum state;
    private ContextData context;

    public FSMContext() {
    }

    public FSMContext(FSMStateEnum state, ContextData context) {
        this.state = state;
        this.context = context;
    }

    public FSMStateEnum getState() {
        return state;
    }

    public void setState(FSMStateEnum state) {
        this.state = state;
    }

    public ContextData getContext() {
        return context;
    }

    public void setContext(ContextData context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "FSMContext{" +
                "state=" + state +
                ", context=" + context +
                '}';
    }
}
