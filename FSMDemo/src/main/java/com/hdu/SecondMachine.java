package com.hdu;

import com.hdu.SecondFSM.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午4:03
 */
public class SecondMachine {
    private FsmState abnormalState;
    private FsmState normalState;
    private FsmState overLoadState;
    private FsmState settingState;
    private FsmState bannedState;

    private volatile FsmState state;


    public SecondMachine() {
        this.abnormalState = new AbnormalState(this);
        this.bannedState = new BannedState(this);
        this.normalState = new NormalState(this);
        this.overLoadState = new OverLoadState(this);
        this.settingState = new SettingState(this);
        this.state = normalState;
    }

    public void updateState(FsmState state) {
        this.state = state;
    }

    public void switchProxy() {
        state.beforeAction();
        state.switchProxy();
        state.afterAction();
    }

    public FsmState getAbnormalState() {
        return abnormalState;
    }

    public FsmState getNormalState() {
        return normalState;
    }

    public FsmState getOverLoadState() {
        return overLoadState;
    }

    public FsmState getSettingState() {
        return settingState;
    }

    public FsmState getBannedState() {
        return bannedState;
    }

    public void setState(FsmState state) {
        this.state = state;
    }
}
