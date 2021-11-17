package com.hdu.SecondFSM;

import com.hdu.SecondMachine;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:56
 */
public class OverLoadState extends AbstractFsmState {

    private static final StateEnum STATE_ENUM = StateEnum.OVERLOAD;

    public OverLoadState(SecondMachine machine) {
        super(machine);
    }

    public void switchProxy() {
        System.out.println("overload switch proxy");
        machine.setState(machine.getNormalState());
    }

    public int getStateCode() {
        return STATE_ENUM.ordinal();
    }
}
