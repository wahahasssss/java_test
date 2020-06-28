package com.hdu.chapter3_supervisorStrategy;

import akka.actor.AbstractActor;
import akka.actor.Props;
import scala.Option;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 下午3:12
 */
public class Child2Actor extends AbstractActor{
    int state = 0;
    public static Props props(){
        return Props.create(Child2Actor.class);
    }
    @Override
    public void postStop(){
        System.out.println("child2 actor post stop");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
        System.out.println("child2 actor pre restart");
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Exception.class,e->{throw  e;})
                .match(Integer.class,i->state = i)
                .matchEquals("get2",s->getSender().tell(state,getSelf()))
                .build();
    }
}
