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
 * @Date 2018/6/21
 * @Time 上午10:19
 */
public class DemoControlAwareActor extends AbstractActor{
    LoggingAdapter log = Logging.getLogger(getContext().getSystem(),this);
    {
        for (Object msg : new Object[] { "foo", "bar", new CustomControlMessage(),
            PoisonPill.getInstance() }) {
        getSelf().tell(msg, getSelf());
    }
    }
    public static Props props(){
        return Props.create(DemoControlAwareActor.class);
    }



    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.info("control message demo actor stop...");
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
