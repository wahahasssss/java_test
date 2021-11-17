package com.hdu

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/9
 * @Time 下午5:31
 */
case object Swap

class Swapper extends Actor {

  import context._

  var log = Logging(system, this)


  def sayHi: Receive = {
    case Swap => {
      log.info("hi")
      become(sayHo)
    }
  }

  def sayHo: Receive = {
    case Swap => {
      log.info("ho")
      become(sayHi)
    }
  }

  override def receive: Receive = {
    case Swap => {
      log.info("hi")
      become(sayHo)
    }
  }
}

object SwapperApp extends App {

  import com.hdu.Swap._

  val system = ActorSystem("swapperSystem")
  val swap = system.actorOf(Props[Swapper], "swapper")
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
  swap ! Swap
}
