package com.hdu.chapter4_mailbox;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.Envelope;
import akka.dispatch.MailboxType;
import akka.dispatch.MessageQueue;
import akka.dispatch.ProducesMessageQueue;
import com.typesafe.config.Config;
import scala.Option;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/6/21
 * @Time 上午10:41
 */
public class MyCustomUnboundMailbox implements MailboxType,ProducesMessageQueue<MyCustomUnboundMailbox.MyMessageQueue>{
    public static class MyMessageQueue implements MessageQueue,MyUnboundedMessageQueueSemantics{
        private final Queue<Envelope> queue = new ConcurrentLinkedDeque<Envelope>();

        public void enqueue(ActorRef receiver,Envelope handle){
            queue.offer(handle);
        }

        public Envelope dequeue(){
            return queue.poll();
        }


        public int numberOfMessages(){
            return queue.size();
        }

        public boolean hasMessages(){
            return !queue.isEmpty();
        }

        public void cleanUp(ActorRef owner,MessageQueue deadLetters){
            for (Envelope handle:queue){
                deadLetters.enqueue(owner,handle);
            }
        }
    }
    public MyCustomUnboundMailbox(ActorSystem.Settings settings, Config config){

    }

    @Override
    public MessageQueue create(Option<ActorRef> owner, Option<ActorSystem> system) {
        return new MyMessageQueue();
    }
}
