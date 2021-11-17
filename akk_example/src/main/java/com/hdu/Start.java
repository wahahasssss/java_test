package com.hdu;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.hdu.acotors.Greeter;
import com.hdu.acotors.HelloWorld;
import com.hdu.acotors.Machine;
import com.hdu.acotors.Sender;
import com.hdu.iot.DeviceActor;
import com.hdu.iot.Students;
import com.hdu.models.Entiry;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;
import static akka.pattern.PatternsCS.pipe;


public class Start {
    public static final ActorSystem ACTOR_SYSTEM = ActorSystem.create("actor_system", ConfigFactory.load("startapplication"));

    public static void main(String[] args) {
//        System.out.println("begin run ");
//        Config config = ConfigFactory.load("example.conf");
//        ActorSystem system = ActorSystem.create("hello",config);
//        ActorRef actorRef = system.actorOf(Props.create(HelloWorld.class),"h");
//        actorRef.tell(Greeter.Msg.GREET,null);


        try {
            ActorRef sender = ACTOR_SYSTEM.actorOf(Sender.props(), "sender");
            ActorRef machine = ACTOR_SYSTEM.actorOf(Machine.props(), "machine");
            String path = "akka.tcp://iot_system@127.0.0.1:2552/user/device";
            ActorSelection selection = ACTOR_SYSTEM.actorSelection(path);
            selection.tell("test", machine);
            Students students = new Students("ssf", 12);
            students.setEntiry(new Entiry("zzzz"));
            selection.tell(students, ActorRef.noSender());
//            machine.tell(new Machine.Bike(),sender);
//            machine.tell(new Machine.Car(),sender);
//            machine.tell(new Machine.Stop(),sender)

            ask(selection, "test", 10000).whenComplete((r, e) -> {

                System.out.println(".....");
            });
            ask(selection, "test", 100).whenComplete((msg, ex) -> {
                System.out.println("ask reply message is " + msg + "exception: " + ex);
            });

            System.out.println("next");
            ActorRef remoteActorRef = ACTOR_SYSTEM.actorFor(path);
            remoteActorRef.tell("test", machine);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
