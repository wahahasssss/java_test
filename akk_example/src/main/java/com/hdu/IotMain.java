package com.hdu;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.compat.Future;
import com.hdu.iot.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午9:33
 */
public class IotMain {
    public static void main(String[] args){
        Config config = ConfigFactory.load("application");
        ActorSystem actorSystem = ActorSystem.create("iot_system",config);

        try {
            ActorRef supervisor = actorSystem.actorOf(IotSupervisorActor.props(),"supervisor");

            ActorRef device = actorSystem.actorOf(DeviceActor.props("group","device"),"device");
            device.tell(new DeviceActor.ReadTemperature(42L),ActorRef.noSender());
            device.tell(new DeviceActor.RecordTemperature(42L,12),ActorRef.noSender());
            device.tell(new Device.RequestTrackDevice("group1","group2"),ActorRef.noSender());
            ActorRef deviceGroup = actorSystem.actorOf(DeviceGroupActor.props("group1"),"device_group");
            deviceGroup.tell(new Device.RequestTrackDevice("group1","device1"),ActorRef.noSender());

            ActorRef deviceManager = actorSystem.actorOf(DeviceManagerActor.props(32L),"deviceManager");
            deviceManager.tell(new DeviceManagerActor.RequestTrackDevice("ggg","ffff"),ActorRef.noSender());



            System.out.println("Press ENTER to exit the system");


            System.in.read();
        }catch (IOException e){

            e.printStackTrace();
        }finally {
            actorSystem.terminate();
        }
    }
}
