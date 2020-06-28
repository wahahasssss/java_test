package com.hdu.stash;

import akka.Done;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.StashBuffer;

import java.util.concurrent.CompletionStage;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/20
 * @Time 下午4:11
 */
public class StashDemo {
    interface DB{
        CompletionStage<Done> save(String id,String value);
        CompletionStage<String> load(String id);
    }

    public static class DataAccess{
        static interface Command{}
        public static class Save implements Command{
            public final String payload;
            public final ActorRef<Done> replyTo;

            public Save(String payload, ActorRef<Done> replyTo) {
                this.payload = payload;
                this.replyTo = replyTo;
            }
        }

        public static class Get implements Command{
            public final ActorRef<String> replayTo;
            public Get(ActorRef<String> replayTo){
                this.replayTo = replayTo;
            }
        }

        public static class InitialState implements Command{
            public final String value;

            public InitialState(String value) {
                this.value = value;
            }
        }

        static class SaveSuccess implements Command{
            public static final SaveSuccess instance = new SaveSuccess();
            public SaveSuccess(){

            }
        }

        static class DBError implements Command{
            public final RuntimeException cause;

            public DBError(RuntimeException cause) {
                this.cause = cause;
            }
        }


        private final StashBuffer<Command> buffer = StashBuffer.create(100);
        private final String id;
        private final DB db;

        public DataAccess(String id, DB db) {
            this.id = id;
            this.db = db;
        }

        public Behavior<Command> behavior(){
            return Behaviors.setup(ctx->{
                db.load(id)
                        .whenComplete((value,cause)->{
                            if (cause == null){
                                ctx.getSelf().tell(new InitialState(value));
                            }else {
                                ctx.getSelf().tell(new DBError(new RuntimeException(cause)));
                            }
                        });
                return init();
            });
        }

        private Behavior<Command> init(){
            return Behaviors.receive(Command.class)
                    .onMessage(InitialState.class,(ctx,msg)->{
                        return buffer.unstashAll(ctx,active(msg.value));
                    })
                    .onMessage(DBError.class,(ctx,msg)->{
                        throw msg.cause;
                    })
                    .onMessage(Command.class,(ctx,msg)->{
                        buffer.stash(msg);
                        return Behavior.same();
                    })
                    .build();
        }


        private Behavior<Command> active(String state){
            return Behaviors.receive(Command.class)
                    .onMessage(Get.class,(ctx,msg)->{
                        msg.replayTo.tell(state);
                        return Behaviors.same();
                    })
                    .onMessage(Save.class,(ctx,msg)->{
                        db.save(id,msg.payload)
                                .whenComplete((value,cause)->{
                                    if (cause == null){
                                        ctx.getSelf().tell(SaveSuccess.instance);
                                    }else {
                                        ctx.getSelf().tell(new DBError(new RuntimeException(cause)));
                                    }
                                });
                        return saving(state,msg.replyTo);
                    })
                    .build();
        }


        private Behavior<Command> saving(String state,ActorRef<Done> replyTo){
            return Behaviors.receive(Command.class)
                    .onMessageEquals(SaveSuccess.instance,ctx->{
                        replyTo.tell(Done.getInstance());
                        return buffer.unstashAll(ctx,active(state));
                    })
                    .onMessage(DBError.class,(ctx,msg)->{
                        throw msg.cause;
                    })
                    .onMessage(Command.class,(ctx,msg)->{
                        buffer.stash(msg);
                        return Behavior.same();
                    })
                    .build();
        }
    }


    public static void main(String[] args){
        DB dbIml = new DBIml();
        DataAccess dataAccess = new DataAccess("123",dbIml);
        ActorSystem system = ActorSystem.create(dataAccess.behavior(),"command_system");
    }
}
