package com.hdu.cluster;


import akka.actor.typed.*;
import akka.actor.typed.javadsl.*;
import akka.cluster.ClusterEvent;
import akka.cluster.typed.*;
import akka.testkit.TestProbe;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/22
 * @Time 上午10:29
 */
public class ClusterDemo {


    interface Command {
        void execute();
    }

    class Save implements Command{
        @Override
        public void execute() {
            System.out.println("execute something...");
        }
    }

    class Load implements Command{
        public void execute(){
            System.out.println("load something...");
        }
    }
    private static Behavior<Command> behaviors(){
        return Behaviors.receive(Command.class)
                .onMessage(Save.class,(ctx,msg)->{
                    msg.execute();
                    return Behaviors.same();
                })
                .onMessage(Load.class,(ctx,msg)->{
                    msg.execute();
                    return Behaviors.same();
                })
                .build();
    }


    public static void main(String[] args){
        Config config = ConfigFactory.load("clusterApplication");
        ActorSystem system = ActorSystem.create(behaviors(),"cluster_system",config);
        Cluster cluster = Cluster.get(system);

        cluster.manager().tell(Join.create(cluster.selfMember().address()));


        ClusterSingleton singleton = ClusterSingleton.get(system);

//        ActorRef<CounterC>
//        cluster.subscriptions().tell(Subscribe.create(s));

//        cluster.manager().tell(Leave.create(cluster.selfMember().address()));
    }
}
