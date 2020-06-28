package com.hdu.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.Optional;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/21
 * @Time 上午10:00
 */
public class DeviceActor extends AbstractActor{

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(),this);

    private final String groupId;
    private final String deviceId;

    public DeviceActor(String groupId, String deviceId) {
        this.groupId = groupId;
        this.deviceId = deviceId;
    }

    public static Props props(String groupId,String deviceId){
        return Props.create(DeviceActor.class,()->new DeviceActor(groupId,deviceId));
    }

    public static final class ReadTemperature{
        long requestId;

        public ReadTemperature(long requestId) {
            this.requestId = requestId;
        }
    }

    public static final class RespondTemperature{
        long requestId;
        Optional<Double> value;

        public RespondTemperature(long requestId, Optional<Double> value) {
            this.requestId = requestId;
            this.value = value;
        }
    }

    public static final class RecordTemperature {
        final long requestId;
        final double value;

        public RecordTemperature(long requestId, double value) {
            this.requestId = requestId;
            this.value = value;
        }
    }

    public static final class TemperatureRecorded {
        final long requestId;

        public TemperatureRecorded(long requestId) {
            this.requestId = requestId;
        }
    }
    Optional<Double> lastTemperatureReading = Optional.empty();

    @Override
    public void preStart() {
        log.info("Device actor {}-{} started,actor path is {}", groupId, deviceId,getSelf().path().toString());
    }

    @Override
    public void postStop() {
        log.info("Device actor {}-{} stopped", groupId, deviceId);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReadTemperature.class,(r)->{
                    log.info(String.format("device temp is read going..."));
                    getSender().tell(new RespondTemperature(r.requestId,lastTemperatureReading),getSelf());
                })
                .match(RecordTemperature.class,r -> {
                    log.info("Recorded temperature reading {} with {}", r.value, r.requestId);
                    lastTemperatureReading = Optional.of(r.value);
                    getSender().tell(new TemperatureRecorded(r.requestId), getSelf());
                })
                .matchEquals("test",r-> {
                    log.info("this is just for testing ,sender is " + getSender().path().toString());
                    getSender().tell("machinezz", ActorRef.noSender());
                })
                .match(Device.RequestTrackDevice.class, r -> {
                    if (this.groupId.equals(r.groupId) && this.deviceId.equals(r.deviceId)) {
                        getSender().tell(new Device.DeviceRegistered(), getSelf());
                    } else {
                        log.warning(
                                "Ignoring TrackDevice request for {}-{}.This actor is responsible for {}-{}.",
                                r.groupId, r.deviceId, this.groupId, this.deviceId
                        );
                    }
                })
                .match(Students.class,s->{
                    log.info("receive message students {}",s.toString());
                })
                .build();
    }
}
