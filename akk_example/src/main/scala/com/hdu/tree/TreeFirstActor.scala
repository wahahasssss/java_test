package com.hdu.tree

import akka.actor.{Actor, Props}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/22
 * @Time 上午11:22
 */

object TreeFirstActor {
  def props(name: String): Props = Props(classOf[TreeFirstActor], name);
}

class TreeFirstActor extends Actor {


  override def preStart(): Unit = {
    println("first actor start ..." + self.path)
  }

  override def postStop(): Unit = {
    print("first actor stop..." + self.path)
  }


  override def receive: Receive = {
    case msg: CreateSecond => {
      var actor = context.actorOf(SecondActor.props("second"))
      actor ! "test"
    }
  }


}
