package com.hdu.acotors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/20
 * @Time 下午4:37
 */
public class Machine extends AbstractActor {
    private final LoggingAdapter LOG = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(Machine.class, () -> new Machine());
    }

    static public class Bike {
        public void run() {
            System.out.println("the bike is running");
        }
    }

    static public class Car {
        public void run() {
            System.out.println("the car is running");
        }
    }

    static public class Stop {
        public void stop() {
            System.out.println("the machine is stopping");
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Machine.Bike.class, o -> {
                    o.run();
                    getSender().tell(new Sender.SendHandle("this is a bike"), ActorRef.noSender());
                })
                .match(Machine.Stop.class, (s) -> {
                    s.stop();
                    getSender().tell("stop", ActorRef.noSender());
                })
                .matchEquals("machine", o -> {
                    LOG.info("========>>>>>>>>...");
                })
                .match(Machine.Car.class, o -> {
                    o.run();
                    getSender().tell(new Sender.SendHandle("this is a car"), ActorRef.noSender());
                }).build();
    }
}
