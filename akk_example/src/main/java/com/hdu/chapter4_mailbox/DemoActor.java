package com.hdu.chapter4_mailbox;

import akka.actor.AbstractActor;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 下午7:21
 */
public class DemoActor extends AbstractActor{
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(),this);
    {
        for (Object msg:new Object[]{
                "lowpriority", "lowpriority",
                "highpriority", "pigdog", "pigdog2", "pigdog3", "highpriority",
                PoisonPill.getInstance()
        }){
            getSelf().tell(msg,getSelf());
        }
    }
    public static Props props(){
        return Props.create(DemoActor.class);
    }



    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("demo actor stop...");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchAny(msg->{
                    log.info(msg.toString());
                })
                .build();
    }
}
