package com.hdu.streams;

import akka.actor.ActorContext;
import akka.actor.ActorRefFactory;
import akka.actor.typed.ActorRef;
import akka.japi.JavaPartialFunction;
import akka.stream.*;
import akka.stream.impl.ExtendedActorMaterializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.stream.typed.javadsl.ActorSource;
import akka.stream.ActorMaterializer;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/20
 * @Time 下午5:42
 */

public class ActorSourceDemo {

    interface Protocol {
    }

    public static class Message implements Protocol {
        private final String msg;

        public Message(String msg) {
            this.msg = msg;
        }
    }

    class Complete implements Protocol {
    }

    class Fail implements Protocol {
        private final Exception ex;

        public Fail(Exception ex) {
            this.ex = ex;
        }
    }

    public static void main(String[] args) {
        final JavaPartialFunction<Protocol, Throwable> failureMatcher = new JavaPartialFunction<Protocol, Throwable>() {
            @Override
            public Throwable apply(Protocol x, boolean isCheck) throws Exception {
                if (x instanceof Fail)
                    return ((Fail) x).ex;
                else
                    throw noMatch();
            }
        };


        final Source<Protocol, ActorRef<Protocol>> source = ActorSource.actorRef(
                (m) -> m instanceof Complete,
                failureMatcher,
                8,
                OverflowStrategy.fail()
        );

        final ActorRef<Protocol> ref = source.collect(new JavaPartialFunction<Protocol, String>() {
            @Override
            public String apply(Protocol x, boolean isCheck) throws Exception {
                if (x instanceof Message) {
                    return ((Message) x).msg;
                } else {
                    throw noMatch();
                }
            }
        }).to(Sink.foreach(System.out::println)).run(null);
        final Message msg = new Message("msg");
        ref.tell(msg);
    }
}


