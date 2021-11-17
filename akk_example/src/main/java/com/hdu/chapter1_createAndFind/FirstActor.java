package com.hdu.chapter1_createAndFind;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;


/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/12
 * @Time 下午2:35
 */
public class FirstActor extends AbstractActor {

    private String actorId;

    public FirstActor(String actorId) {
        this.actorId = actorId;
    }

    public static Props props(String actorId) {
        return Props.create(FirstActor.class, () -> new FirstActor(actorId));
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(10, Duration.create(1, TimeUnit.MINUTES),
                DeciderBuilder
                        .match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                        .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                        .match(IllegalArgumentException.class, e -> SupervisorStrategy.stop())
                        .matchAny(o -> SupervisorStrategy.escalate())
                        .build());
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("CREATE", (r) -> {
                    System.out.println("this is first actor:path is" + getSelf().path().toString());
                    ActorRef second = context().system().actorOf(SecondActor.props(), "second");
                    ActorRef second2 = context().actorOf(SecondActor.props(), "second2");
                    ActorRef second3 = context().actorOf(SecondActor.props(), "second3");


                    ActorRef ref = context().actorFor("second22");
                    ref.tell("print", getSelf());
                    second.tell("print", getSelf());
                    second2.tell("print", getSelf());

                    second3.forward("print", getContext());
                    Iterator iterator = getContext().getChildren().iterator();
                    while (iterator.hasNext()) {
                        ActorRef ref1 = (ActorRef) iterator.next();
                        System.out.println("iterator path is " + ref1.path().toString());
                    }

                })
                .build();
    }
}
