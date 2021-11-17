package com.hdu.mailbox

import akka.actor.Actor
import akka.event.{Logging, LoggingAdapter}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 下午8:41
 */
class Logger extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  self ! 'lowpriority
  self ! 'lowpriority
  self ! 'highpriority


  override def receive: Receive = {
    case x => {
      log.info(x.toString)
      println(x.toString)
    }
  }

}
