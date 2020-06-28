package com.hdu.SecondFSM;

import com.hdu.SecondMachine;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午3:55
 */
public class NormalState extends AbstractFsmState{


    private static final StateEnum STATE_ENUM = StateEnum.NORMAL;
    public NormalState(SecondMachine machine) {
        super(machine);
    }

    public void switchProxy() {
        System.out.println("normal proxy ,do not need to switch");
    }

    public int getStateCode() {
        return STATE_ENUM.ordinal();
    }
}
