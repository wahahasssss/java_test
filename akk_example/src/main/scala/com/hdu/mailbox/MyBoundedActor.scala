package com.hdu.mailbox

import akka.actor.{Actor, Props}
import akka.dispatch.{BoundedMessageQueueSemantics, NonBlockingBoundedMailbox, RequiresMessageQueue}
import com.hdu.actors.MyActor

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 下午8:18
 */

object MyBoundedActor {
  def props: Props = Props[MyBoundedActor]
}

class MyBoundedActor extends Actor with RequiresMessageQueue[BoundedMessageQueueSemantics] {

  NonBlockingBoundedMailbox

  override def receive: Receive = {
    case _ => println("this is a bounded actor")
  }
}


