package com.hdu.chapter1_createAndFind;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/12
 * @Time 下午2:45
 */
// actor的创建和查找
public class SupervisorMain {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("supervisor");
        ActorRef first = system.actorOf(FirstActor.props("1"),"first");
        ActorRef supervisor = system.actorOf(FirstActor.props("2"),"supervisor");
        first.tell("CREATE",supervisor);


    }
}
