package com.hdu.tree

import akka.actor.{Actor, Props}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/22
 * @Time 上午11:28
 */
object SecondActor {
  def props(name: String): Props = Props(classOf[SecondActor], name);
}

class SecondActor extends Actor {


  override def preStart(): Unit = {
    println("second actor start..." + self.path)
  }

  override def postStop(): Unit = {
    println("second actor stop...." + self.path)
  }

  override def receive: Receive = {
    case s: String => {
      println("second actor received msg" + s)
    }
  }
}
