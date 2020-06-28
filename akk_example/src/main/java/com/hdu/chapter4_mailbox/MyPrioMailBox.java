package com.hdu.chapter4_mailbox;

import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.dispatch.Envelope;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedStablePriorityMailbox;
import com.typesafe.config.Config;

import java.util.Comparator;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 下午7:08
 */
public class MyPrioMailBox extends UnboundedStablePriorityMailbox{
    public MyPrioMailBox(Comparator<Envelope> cmp, int initialCapacity) {
        super(cmp, initialCapacity);
    }

    public MyPrioMailBox(ActorSystem.Settings settings, Config config){
        super(new PriorityGenerator() {
            @Override
            public int gen(Object message) {
                if (message.equals("highpriority"))
                    return 0;
                else if (message.equals("lowpriority"))
                    return 2;
                else if (message.equals(PoisonPill.getInstance()))
                    return 3;
                else
                    return 1;
            }
        });
    }
}
