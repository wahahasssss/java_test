package com.hdu.actors


import akka.actor.OneForOneStrategy
import akka.actor.SupervisorStrategy
import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}

import scala.concurrent.duration._
import akka.actor.{Actor, Props, ReceiveTimeout, Timers}
import com.hdu.actors.MyActor.{FirstTick, TickKey}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/9
 * @Time 下午4:57
 */
object MyActor {

  private case object TickKey

  private case object FirstTick;

  private case object Tick;

  def props: Props = Props(new MyActor(12))
}

class MyActor(age: Integer) extends Actor with Timers {

  import com.hdu.actors.MyActor._;

  override val supervisorStrategy = OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute) {
    case _: ArithmeticException => Resume
    case _: NullPointerException => Restart
    case _: IllegalArgumentException => Stop
    case _: Exception => Escalate
  }

  context.setReceiveTimeout(50 seconds)
  timers.startSingleTimer(TickKey, FirstTick, 500.millis)

  override def receive: Receive = {
    case FirstTick =>
      timers.startPeriodicTimer(TickKey, FirstTick, 1.second)
    case Tick =>
      println("tick is running....")
    case ReceiveTimeout => {
      context.setReceiveTimeout(Duration.Undefined)
      println("the message is timeout...")
      throw new RuntimeException("actor receive message time out")
    }
  }
}
