package com.hdu.acotors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/20
 * @Time 下午4:49
 */
public class Sender extends AbstractActor {
    private String path;

    public static Props props() {
        return Props.create(Sender.class, () -> new Sender());
    }


    static public class SendHandle {
        private String message;

        public SendHandle(String message) {
            this.message = message;
        }

        public void handle() {
            System.out.println(String.format("handle message is %s", message));
        }
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("sender will be stopped");
    }

    @Override
    public Receive createReceive() {
        path = getSelf().path().address().toString();
        System.out.println(getSelf().path());
        ActorRef secondActor = getContext().actorOf(Props.empty(), "second");
        System.out.println(secondActor.path());
        System.out.println(path);
        return receiveBuilder()
                .match(SendHandle.class, SendHandle::handle)
                .matchEquals("stop", o -> {
                    getContext().stop(getSelf());
                })
                .build();
    }
}
