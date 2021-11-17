package com.hdu.chapter1_createAndFind;

import akka.actor.AbstractActor;
import akka.actor.Props;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/12
 * @Time 下午2:36
 */
public class SecondActor extends AbstractActor {

    public static Props props() {
        return Props.create(SecondActor.class);
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("second actor is stopped...");
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("print", (r) -> {
                    Thread.sleep(1000);
                    System.out.println("this is second actor:path is " + getSelf().path().toString() + ",sender is " + getSender().path().toString());

                })
                .match(Integer.class, (i) -> {
                    Thread.sleep(1000);

                    System.out.println("this is second actor:path is " + getSelf().path().toString() + ",sender is " + getSender().path().toString() + ", number is " + i);
                })
                .build();
    }
}
