package com.hdu.chapter3_supervisorStrategy;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 上午10:52
 */
public class SupervisorActor extends AbstractActor {

    public static Props props() {
        return Props.create(SupervisorActor.class);
    }

    private SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.create(1, TimeUnit.MINUTES),
            DeciderBuilder
                    .match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                    .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                    .match(MyException.class, e -> {
                        System.out.println("the actor " + getSender().path() + " get a exception" + e.getMessage());
                        return SupervisorStrategy.stop();
                    })
                    .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
                    .matchAny(o -> SupervisorStrategy.escalate())
                    .build());
//    private SupervisorStrategy strategy = new AllForOneStrategy(10, Duration.create(1, TimeUnit.MINUTES),
//            DeciderBuilder
//                    .match(ArithmeticException.class,e-> SupervisorStrategy.resume())
//                    .match(NullPointerException.class,e->SupervisorStrategy.restart())
//                    .match(IllegalArgumentException.class,e->SupervisorStrategy.stop())
//                    .matchAny(o->SupervisorStrategy.escalate())
//                    .build());

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Props.class, props -> {
                    System.out.println("send is " + getSender().path());
                    ActorRef childActor = getContext().actorOf(props, "child");
                    getContext().watch(childActor);
                    getSender().tell(childActor, getSelf());

                })
                .match(Terminated.class, r -> {
                    System.out.println("the watched actor stopped, actor information is " + r.getActor());
                })
                .build();
    }
}
