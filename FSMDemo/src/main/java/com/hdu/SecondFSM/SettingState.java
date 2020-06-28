package com.hdu.SecondFSM;

import com.hdu.SecondMachine;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:57
 */
public class SettingState extends AbstractFsmState{
    private static final StateEnum STATE_ENUM = StateEnum.SETTING;
    public SettingState(SecondMachine machine) {
        super(machine);
    }

    public void switchProxy() {
        System.out.println(String.format("setting switch proxy is running"));
        machine.updateState(machine.getNormalState());
    }

    public int getStateCode() {
        return STATE_ENUM.ordinal();
    }
}
