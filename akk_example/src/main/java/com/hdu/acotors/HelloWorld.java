package com.hdu.acotors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWorld extends UntypedActor{
    ActorRef greeter;

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class),"greeter");
        greeter.tell(Greeter.Msg.GREET,getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (Greeter.Msg.DONE == message){
            greeter.tell(Greeter.Msg.DONE,getSelf());
            getContext().stop(getSelf());
        }else if (Greeter.Msg.GREET == message){
            System.out.println("hello welcome to come thr world,you will be /..");
        }
    }
}
