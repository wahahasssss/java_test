package com.hdu.chapter2_stop;

import akka.actor.*;
import akka.japi.pf.DeciderBuilder;
import scala.concurrent.duration.Duration;

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
    private Integer count = 0;

    public FirstActor(String actorId) {
        this.actorId = actorId;
    }

    public static Props props(String actorId) {
        return Props.create(FirstActor.class, () -> new FirstActor(actorId));
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("first actor " + getSelf().path() + " is stopped...");
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return new OneForOneStrategy(10, Duration.create(1, TimeUnit.MINUTES),
                DeciderBuilder
                        .match(ArithmeticException.class, e -> SupervisorStrategy.resume())
                        .match(NullPointerException.class, e -> SupervisorStrategy.restart())
                        .match(ActorKilledException.class, e -> {
                            System.out.println("the actor killed ," + getSender().path());
                            return SupervisorStrategy.restart();
                        })
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
                    getContext().watch(second2);
                    getContext().watch(second3);
                    //测试PoisonPill和stop 的区别
//                    for (int i =0;i<10;i++){
//                        second2.tell(i,getSelf());
//                        second3.tell(i,getSelf());
//                    }
//                    Thread.sleep(5000);
//                    getContext().stop(second3);
//                    second3.tell(Kill.getInstance(),ActorRef.noSender());
//                    second2.tell(PoisonPill.getInstance(),ActorRef.noSender());

                })
                .match(Terminated.class, s -> {
                    System.out.println("first actor received terminated message , send is " + getSender().path().name());
                })
                .matchEquals("x", (r) -> {
                    System.out.println("count value is " + count);
                    Thread.sleep(2000);
                    count++;
                    System.out.println("count add result is " + count);
                })
                .build();
    }
}
