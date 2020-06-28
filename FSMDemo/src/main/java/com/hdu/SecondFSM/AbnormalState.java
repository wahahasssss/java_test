package com.hdu.SecondFSM;

import com.hdu.SecondMachine;
import com.hdu.action.StateEnum;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午4:02
 */
public class AbnormalState extends AbstractFsmState{

    private final static StateEnum STATE_ENUM = StateEnum.ABNORMAL;
    public AbnormalState(SecondMachine machine) {
        super(machine);
    }

    public void switchProxy() {
        System.out.println(String.format("abnormal switch proxy ..."));
        machine.updateState(machine.getNormalState());
    }

    @Override
    public int getStateCode() {
        return STATE_ENUM.ordinal();
    }
}
