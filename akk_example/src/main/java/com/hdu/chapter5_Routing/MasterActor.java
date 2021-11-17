package com.hdu.chapter5_Routing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/21
 * @Time 下午7:14
 */
public class MasterActor extends AbstractActor {
    Router router;

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef r = getContext().actorOf(Props.create(Work.class));
            getContext().watch(r);
            routees.add(new ActorRefRoutee(r));

        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Work.class, msg -> {
                    router.route(msg, getSender());
                })
                .match(Terminated.class, msg -> {
                    router = router.removeRoutee(msg.actor());
                    ActorRef r = getContext().actorOf(Props.create(Work.class));
                    getContext().watch(r);
                    router = router.addRoutee(new ActorRefRoutee(r));
                })
                .build();
    }
}
