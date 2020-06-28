package com.hdu.acotors;

import akka.actor.UntypedActor;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Greeter extends UntypedActor{
    public static enum Msg{
        GREET,DONE;
    }
    @Override
    public void onReceive(Object message) throws Throwable {
        if (Msg.GREET == message){
            System.out.println("hello world");
            getSender().tell(Msg.DONE,getSelf());
        }else {
            unhandled(message);
            System.out.println("msg is done");
        }
    }
}
