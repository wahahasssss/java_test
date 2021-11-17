package com.hdu.supervisor

import akka.actor.Actor

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午8:46
 */
class ChildActor extends Actor {
  var state = 0

  override def receive: Receive = {
    case ex: Exception => throw ex
    case x: Int => {
      state = x
      println(s"the state value is ${state}")
    }
    case "get" => sender() ! state
  }

}
