package com.hdu.kafka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/27
 * @Time 5:35 PM
 */
public class KafkaMain {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("test-system");
        ActorRef consumer = actorSystem.actorOf(ConsumerDemo.props(), "consumer");
        consumer.tell("start", ActorRef.noSender());
    }
}
