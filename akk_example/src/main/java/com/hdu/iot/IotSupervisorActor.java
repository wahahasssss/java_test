package com.hdu.iot;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午9:23
 */
public class IotSupervisorActor extends AbstractActor{
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().getSystem(),this);
    public static Props props(){
        return Props.create(IotSupervisorActor.class);
    }


    @Override
    public void postStop() throws Exception {
        loggingAdapter.info("stop iot supervisor...");
    }

    @Override
    public void preStart() throws Exception {
        loggingAdapter.info("iot supervisor start");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .build();
    }

}
