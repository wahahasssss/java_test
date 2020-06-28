package com.hdu.chapter4_mailbox;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/20
 * @Time 下午7:14
 */
public class MailboxMain {
    public static void main(String[] args) {
        Config config = ConfigFactory.load("application_mailbox");
        ActorSystem system = ActorSystem.create("mailbox_system", config);
        ActorRef myActor = system.actorOf(DemoActor.props().withDispatcher("prio-dispatcher"));
        ActorRef controlMessageActor = system.actorOf(DemoControlAwareActor.props().withDispatcher("control-aware-dispatcher"));


//        ActorRef customActor = system.actorOf(MyCustomMailBoxDemoActor.props().withDispatcher("custom-dispatcher-mailbox"));
//        customActor.tell("t",ActorRef.noSender());
    }
}
