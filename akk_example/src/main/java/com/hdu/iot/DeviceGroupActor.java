package com.hdu.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午10:35
 */
public class DeviceGroupActor extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private String groupId;

    public DeviceGroupActor(String groupId) {
        this.groupId = groupId;
    }

    public static Props props(String groupId) {
        return Props.create(DeviceGroupActor.class, () -> new DeviceGroupActor(groupId));
    }

    private final Map<String, ActorRef> deviceId2Actor = new HashMap<>();
    private final Map<ActorRef, String> actor2DeviceId = new HashMap<>();

    @Override
    public void preStart() {
        log.info("DeviceGroup {} started", groupId);
    }

    @Override
    public void postStop() {
        log.info("DeviceGroup {} stopped", groupId);
    }

    private void onTrackDevice(Device.RequestTrackDevice trackMsg) {
        if (this.groupId.equals(trackMsg.groupId)) {
            ActorRef deviceActor = deviceId2Actor.get(trackMsg.deviceId);
            if (deviceActor != null) {
                deviceActor.forward(trackMsg, getContext());
            } else {
                log.info("Creating device actor for {}", trackMsg.deviceId);
                deviceActor = getContext().actorOf(DeviceActor.props(groupId, trackMsg.deviceId), "device-" + trackMsg.deviceId);
                deviceId2Actor.put(trackMsg.deviceId, deviceActor);
                actor2DeviceId.put(deviceActor, trackMsg.deviceId);
                deviceActor.forward(trackMsg, getContext());
            }
        } else {
            log.warning(
                    "Ignoring TrackDevice request for {}. This actor is responsible for {}.",
                    groupId, this.groupId
            );
        }
    }

    private void onTerminated(Terminated t) {
        ActorRef deviceActor = t.getActor();
        String deviceId = actor2DeviceId.get(deviceActor);
        log.info("Device actor for {} has been terminated", deviceId);
        actor2DeviceId.remove(deviceActor);
        deviceId2Actor.remove(deviceId);
    }

    private void onDeviceList(RequestDeviceList r) {
        getSender().tell(new DeviceGroupActor.ReplyDeviceList(r.requestId, deviceId2Actor.keySet()), getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Device.RequestTrackDevice.class, this::onTrackDevice)
                .match(Terminated.class, this::onTerminated)
                .build();
    }


    public static final class RequestDeviceList {
        final long requestId;

        public RequestDeviceList(long requestId) {
            this.requestId = requestId;
        }
    }

    public static final class ReplyDeviceList {
        final long requestId;
        final Set<String> ids;

        public ReplyDeviceList(long requestId, Set<String> ids) {
            this.requestId = requestId;
            this.ids = ids;
        }
    }


    public static final class RequestAllTemperatures {
        final long requestId;

        public RequestAllTemperatures(long requestId) {
            this.requestId = requestId;
        }
    }

    public static final class RespondAllTemperatures {
        final long requestId;
        final Map<String, TemperatureReading> temperatures;

        public RespondAllTemperatures(long requestId, Map<String, TemperatureReading> temperatures) {
            this.requestId = requestId;
            this.temperatures = temperatures;
        }
    }

    public static interface TemperatureReading {
    }

    public static final class Temperature implements TemperatureReading {
        public final double value;

        public Temperature(double value) {
            this.value = value;
        }
    }

    public static final class TemperatureNotAvailable implements TemperatureReading {
    }

    public static final class DeviceNotAvailable implements TemperatureReading {
    }

    public static final class DeviceTimedOut implements TemperatureReading {
    }
}
