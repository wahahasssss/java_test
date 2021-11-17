package com.hdu.squirrelfsm;

import com.hdu.SecondFSM.FsmState;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:27
 */
@StateMachineParameters(stateType = FSMStateEnum.class, eventType = FSMEventEnum.class, contextType = FSMContext.class)
public class MyStateMachine extends AbstractUntypedStateMachine {
    /**
     * 从状态a 到状态b 事件触发的action
     *
     * @param from
     * @param to
     * @param event
     * @param context
     */
    protected void fromNormalToAbnormal(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("from %s to %s,event is %s ", from.getDescription(), to.getDescription(), event.getDescription(), context.toString()));
    }

    protected void ontoAbnormal(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("Entry to state is %s", to.getDescription()));
    }


    protected void fromNormalToOverload(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("from %s to %s,event is %s ", from.getDescription(), to.getDescription()));
    }

    protected void ontoOverload(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("Entry to state is %s", to.getDescription()));
    }


    protected void fromAbnormalToNormal(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("from %s to %s,event is %s", from.getDescription(), to.getDescription()));

    }

    protected void ontoNormal(FSMStateEnum from, FSMStateEnum to, FSMEventEnum event, FSMContext context) {
        System.out.println(String.format("Entry to state is %s", to.getDescription()));
    }
}
