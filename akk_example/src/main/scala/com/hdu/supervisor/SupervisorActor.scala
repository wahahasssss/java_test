package com.hdu.supervisor

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, Props}

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/10
  * @Time 下午8:42
  */
class SupervisorActor extends Actor{
  import scala.concurrent.duration._


  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10,withinTimeRange = 1 minutes){
      case _:ArithmeticException => Resume
      case _:NullPointerException => Restart
      case _:IllegalArgumentException => Stop
      case _:Exception => Escalate
    }

  override def receive: Receive = {
    case p:Props => sender() ! context.actorOf(p)
  }
}
