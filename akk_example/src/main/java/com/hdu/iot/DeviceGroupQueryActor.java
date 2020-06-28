package com.hdu.iot;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.FiniteDuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午11:19
 */
public class DeviceGroupQueryActor extends AbstractActor{
    public static final class CollectionTimeout {
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    final Map<ActorRef, String> actorToDeviceId;
    final long requestId;
    final ActorRef requester;

    Cancellable queryTimeoutTimer;

    public DeviceGroupQueryActor(Map<ActorRef, String> actorToDeviceId, long requestId, ActorRef requester, FiniteDuration timeout) {
        this.actorToDeviceId = actorToDeviceId;
        this.requestId = requestId;
        this.requester = requester;

        queryTimeoutTimer = getContext().getSystem().scheduler().scheduleOnce(
                timeout, getSelf(), new CollectionTimeout(), getContext().dispatcher(), getSelf()
        );
    }

    public static Props props(Map<ActorRef, String> actorToDeviceId, long requestId, ActorRef requester, FiniteDuration timeout) {
        return Props.create(DeviceGroupQueryActor.class, actorToDeviceId, requestId, requester, timeout);
    }

    @Override
    public void preStart() {
        for (ActorRef deviceActor : actorToDeviceId.keySet()) {
            getContext().watch(deviceActor);
            deviceActor.tell(new DeviceActor.ReadTemperature(0L), getSelf());
        }
    }

    @Override
    public void postStop() {
        queryTimeoutTimer.cancel();
    }
    @Override
    public Receive createReceive() {
        return waitingForReplies(new HashMap<>(), actorToDeviceId.keySet());
    }

    public Receive waitingForReplies(
            Map<String, DeviceGroupActor.TemperatureReading> repliesSoFar,
            Set<ActorRef> stillWaiting) {
        return receiveBuilder()
                .match(DeviceActor.RespondTemperature.class, r -> {
                    ActorRef deviceActor = getSender();
                    DeviceGroupActor.TemperatureReading reading = r.value
                            .map(v -> (DeviceGroupActor.TemperatureReading) new DeviceGroupActor.Temperature(v))
                            .orElse(new DeviceGroupActor.TemperatureNotAvailable());
                    receivedResponse(deviceActor, reading, stillWaiting, repliesSoFar);
                })
                .match(Terminated.class, t -> {
                    receivedResponse(t.getActor(), new DeviceGroupActor.DeviceNotAvailable(), stillWaiting, repliesSoFar);
                })
                .match(CollectionTimeout.class, t -> {
                    Map<String, DeviceGroupActor.TemperatureReading> replies = new HashMap<>(repliesSoFar);
                    for (ActorRef deviceActor : stillWaiting) {
                        String deviceId = actorToDeviceId.get(deviceActor);
                        replies.put(deviceId, new DeviceGroupActor.DeviceTimedOut());
                    }
                    requester.tell(new DeviceGroupActor.RespondAllTemperatures(requestId, replies), getSelf());
                    getContext().stop(getSelf());
                })
                .build();
    }

    public void receivedResponse(ActorRef deviceActor,
                                 DeviceGroupActor.TemperatureReading reading,
                                 Set<ActorRef> stillWaiting,
                                 Map<String, DeviceGroupActor.TemperatureReading> repliesSoFar) {
        getContext().unwatch(deviceActor);
        String deviceId = actorToDeviceId.get(deviceActor);

        Set<ActorRef> newStillWaiting = new HashSet<>(stillWaiting);
        newStillWaiting.remove(deviceActor);

        Map<String, DeviceGroupActor.TemperatureReading> newRepliesSoFar = new HashMap<>(repliesSoFar);
        newRepliesSoFar.put(deviceId, reading);
        if (newStillWaiting.isEmpty()) {
            requester.tell(new DeviceGroupActor.RespondAllTemperatures(requestId, newRepliesSoFar), getSelf());
            getContext().stop(getSelf());
        } else {
            getContext().become(waitingForReplies(newRepliesSoFar, newStillWaiting));
        }
    }
}
