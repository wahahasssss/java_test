package com.hdu.receptionist;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.receptionist.Receptionist;
import akka.actor.typed.receptionist.ServiceKey;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/19
 * @Time 下午5:33
 */
public class ReceptionistDemo {
    static final ServiceKey PingServiceKey = ServiceKey.create(Ping.class,"pingservice");

    public static class Pong{}

    public static class Ping{
        private final ActorRef<Pong> replyTo;
        Ping(ActorRef<Pong> replyTo){
            this.replyTo = replyTo;
        }
    }

    static Behavior<Ping> pingService(){
        return Behaviors.setup((ctx)->{
            ctx.getSystem().receptionist()
                    .tell(Receptionist.register(PingServiceKey,ctx.getSelf()));
        return Behaviors.receive(Ping.class)
                .onMessage(Ping.class,(c,msg)->{
                    msg.replyTo.tell(new Pong());
                    return Behaviors.same();
                }).build();
        });
    }


    static Behavior<Pong> pinger(ActorRef<Ping> pingService){
        return Behaviors.setup((ctx)->{
            pingService.tell(new Ping(ctx.getSelf()));
            return Behaviors.receive(Pong.class)
                    .onMessage(Pong.class,(c,msg)->{
                        System.out.println("I was ponged! "+ msg);
                        return Behaviors.same();
                    }).build();
        });
    }

    static Behavior<Void> guardian() {
        return Behaviors.setup((ctx) -> {
            ctx.getSystem().receptionist()
                    .tell(Receptionist.subscribe(PingServiceKey, ctx.getSelf().narrow()));
            ActorRef<Ping> ps = ctx.spawnAnonymous(pingService());
            ctx.watch(ps);
            return Behaviors.receive(Object.class)
                    .onMessage(Receptionist.Listing.class, listing -> listing.isForKey(PingServiceKey), (c, msg) -> {
                        msg.getServiceInstances(PingServiceKey).forEach(ar -> ctx.spawnAnonymous(pinger((ActorRef<Ping>) ar)));
                        return Behaviors.same();
                    }).build();
        }).narrow();
    }
}
