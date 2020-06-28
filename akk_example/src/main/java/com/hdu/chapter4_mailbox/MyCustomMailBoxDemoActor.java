package com.hdu.chapter4_mailbox;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.dispatch.RequiresMessageQueue;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/21
 * @Time 上午10:57
 */
public class MyCustomMailBoxDemoActor extends AbstractActor implements RequiresMessageQueue<MyUnboundedMessageQueueSemantics>{

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(),this);

    public static Props props(){
        return Props.create(MyCustomMailBoxDemoActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(o->{
                    log.info(o.toString());
                })
                .build();
    }
}
