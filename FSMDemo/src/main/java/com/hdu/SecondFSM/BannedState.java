package com.hdu.SecondFSM;

import com.hdu.SecondMachine;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午4:14
 */
public class BannedState extends AbstractFsmState{

    private static final StateEnum STATE_ENUM = StateEnum.BANNED;
    public BannedState(SecondMachine machine) {
        super(machine);
    }

    public void switchProxy() {
        //todo
        System.out.println("banned proxy had switched the proxy");
        machine.setState(machine.getNormalState());
    }

    public int getStateCode() {
        return STATE_ENUM.ordinal();
    }
}
