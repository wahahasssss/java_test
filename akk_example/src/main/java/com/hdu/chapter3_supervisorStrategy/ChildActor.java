package com.hdu.chapter3_supervisorStrategy;

import akka.actor.AbstractActor;
import scala.Option;
import sun.jvm.hotspot.debugger.win32.coff.MachineTypes;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 上午11:16
 */
public class ChildActor extends AbstractActor {
    int state = 0;

//    @Override
//    public void preStart() throws Exception {
//        System.out.println("child actor pre start");
//    }

    @Override
    public void postStop() {
        System.out.println("child actor post stop");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
        System.out.println("child actor pre restart");
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Exception.class, e -> {
                    throw e;
                })
                .match(Integer.class, i -> state = i)
                .matchEquals("get", s -> getSender().tell(state, getSelf()))
                .matchEquals("throw", s -> {
                    throw new MyException("child get the myexception sssss");
                })
                .build();
    }
}
