package com.hdu.squirrelfsm;

import com.hdu.SecondFSM.FsmState;
import org.squirrelframework.foundation.fsm.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 上午11:26
 */
public class SquirrelFsmMain {
    public static void main(String[] args){
        StateMachineBuilder builder = StateMachineBuilderFactory.create(MyStateMachine.class,FSMStateEnum.class,FSMEventEnum.class,FSMContext.class);
        builder.externalTransition()
                .from(FSMStateEnum.Normal)
                .to(FSMStateEnum.Abnormal)
                .on(FSMEventEnum.ToAbnormal)
                .when(new Condition() {
                    public boolean isSatisfied(Object context) {
                        FSMContext context1 = (FSMContext)context;
                        return context1.getContext().getAge()>3;
                    }
                    public String name() {
                        return "condition";
                    }
                })
                .callMethod("fromNormalToAbnormal");
        builder.externalTransition()
                .from(FSMStateEnum.Normal)
                .to(FSMStateEnum.Overload)
                .on(FSMEventEnum.ToOverload)
                .when(new Condition() {
                    public boolean isSatisfied(Object context) {
                        FSMContext context1 = (FSMContext)context;
                        return context1.getContext().getAge()>3;
                    }

                    public String name() {
                        return "condition_overload";
                    }
                })
                .callMethod("fromNormalToOverload");

        builder.externalTransition()
                .from(FSMStateEnum.Abnormal)
                .to(FSMStateEnum.Normal)
                .on(FSMEventEnum.ToNormal)
                .when(new Condition() {
                    public boolean isSatisfied(Object context) {
                        FSMContext context1 = (FSMContext)context;
                        return context1.getContext().getAge()>3;
                    }

                    public String name() {
                        return "condition normal...";
                    }
                })
        .callMethod("fromAbnormalToNormal");
        builder.onEntry(FSMStateEnum.Abnormal).callMethod("ontoAbnormal");
        builder.onEntry(FSMStateEnum.Overload).callMethod("ontoOverload");
        builder.onEntry(FSMStateEnum.Normal).callMethod("ontoNormal");


        StateMachine fsm = builder.newStateMachine(FSMStateEnum.Normal);
        FSMContext context = new FSMContext();
        context.setContext(new ContextData(5,"zfw"));
        context.setState(FSMStateEnum.Normal);
        fsm.fire(FSMEventEnum.ToAbnormal,context);

//        fsm.fire(FSMEventEnum.ToNormal,context);
//        fsm.fire(FSMEventEnum.ToOverload,context);
        System.out.println("current State is " + fsm.getCurrentState());
    }


}
