package com.hdu.chapter3_supervisorStrategy;


import akka.actor.*;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.time.LocalDate;

import static java.util.concurrent.TimeUnit.SECONDS;
import static akka.pattern.Patterns.ask;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 上午11:22
 */
//supervisor 策略测试
public class SupervisorMain {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("c3_supervisor");
        ActorRef supervisor = system.actorOf(SupervisorActor.props(),"supervisor_actor");
        ActorRef childActor = (ActorRef) Await.result(ask(supervisor, Props.create(ChildActor.class),5000),Duration.create(5,SECONDS));
        //        ActorRef child2Actor = (ActorRef) Await.result(ask(supervisor,Child2Actor.props(),5000),Duration.create(5,SECONDS));


        childActor.tell("throw",ActorRef.noSender());
//        childActor.tell(42,ActorRef.noSender());//发送消息
//        int result1 = (Integer) Await.result(ask(childActor,"get",5000),Duration.create(5,SECONDS));
//        System.out.println("result  is " + result1);
//
//        childActor.tell(new ArithmeticException(),ActorRef.noSender());
        int result = (Integer) Await.result(ask(childActor,"get",5000),Duration.create(5,SECONDS));
//        System.out.println("result  is " + result);

//        childActor.tell(new NullPointerException(),ActorRef.noSender());
//        Thread.sleep(1000);
//        childActor.tell(PoisonPill.getInstance(),ActorRef.noSender());
//        childActor.tell(new IllegalArgumentException(),ActorRef.noSender());





//        int result1 = (Integer) Await.result(ask(childActor,"get",5000),Duration.create(5,SECONDS));
//        System.out.println(result1);


//        Object result2 = Await.result(ask(childActor,"get",5000),Duration.create(5,SECONDS));
//        if (result2 instanceof Terminated){
//            System.out.println("Terminated");
//        }
    }
}
