package com.hdu.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午11:00
 */
public class DeviceManagerActor extends AbstractActor{

    private long requestId;
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


    private final Map<String,ActorRef> groupId2Actor = new HashMap<>();
    private final Map<ActorRef,String> actor2GroupId = new HashMap<>();

    public static Props props(long requestId){
        return Props.create(DeviceManagerActor.class,()->new DeviceManagerActor(requestId));
    }

    public DeviceManagerActor(long requestId) {
        this.requestId = requestId;
    }

    @Override
    public void preStart() {
        log.info("DeviceManager started");
    }

    @Override
    public void postStop() {
        log.info("DeviceManager stopped");
    }

    private void onTrackDevice(RequestTrackDevice trackMsg) {
        String groupId = trackMsg.groupId;
        ActorRef ref = groupId2Actor.get(groupId);
        if (ref != null) {
            ref.forward(trackMsg, getContext());
        } else {
            log.info("Creating device group actor for {}", groupId);
            ActorRef groupActor = getContext().actorOf(DeviceGroupActor.props(groupId), "group-" + groupId);
            getContext().watch(groupActor);
            groupActor.forward(trackMsg, getContext());
            groupId2Actor.put(groupId, groupActor);
            actor2GroupId.put(groupActor, groupId);
        }
    }
    private void onTerminated(Terminated t) {
        ActorRef groupActor = t.getActor();
        String groupId = actor2GroupId.get(groupActor);
        log.info("Device group actor for {} has been terminated", groupId);
        actor2GroupId.remove(groupActor);
        groupId2Actor.remove(groupId);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(RequestTrackDevice.class,this::onTrackDevice)
                .match(Terminated.class,this::onTerminated)
                .build();
    }

       public static final class RequestTrackDevice {
        public final String groupId;
        public final String deviceId;

        public RequestTrackDevice(String groupId, String deviceId) {
            this.groupId = groupId;
            this.deviceId = deviceId;
        }
    }

    public static final class DeviceRegistered {
    }

}
