package com.hdu.actors

import akka.actor.Actor

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/9
 * @Time 下午5:25
 */
class HotSwapActor extends Actor {

  import context._

  def angry: Receive = {
    case "foo" => sender()
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender()
    case "foo" => become(angry)
  }

  override def receive: Receive = {
    case "foo" =>
      become(angry)
    case "bar" =>
      become(happy)
  }
}
