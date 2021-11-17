package com.hdu.chapter2_stop;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/12
 * @Time 下午2:45
 */
//停止actor的几种方式
public class SupervisorMain {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("supervisor");
        ActorRef first = system.actorOf(FirstActor.props("1"), "first");
        ActorRef supervisor = system.actorOf(FirstActor.props("2"), "supervisor");
        for (int i = 0; i < 10; i++) {
            first.tell("x", supervisor);
        }
        first.tell("CREATE", supervisor);
////        first.tell(PoisonPill.getInstance(),ActorRef.noSender());
//        system.stop(first);
        ActorSelection selection = system.actorSelection("akka://supervisor/user/first/second2");
        selection.tell("print", ActorRef.noSender());
    }
}
