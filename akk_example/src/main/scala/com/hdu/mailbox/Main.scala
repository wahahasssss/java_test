package com.hdu.mailbox

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 下午8:22
 */
object Main extends App {
  val config = ConfigFactory.load("application")
  var system = ActorSystem("mailbox-system", config)
  val boundedActor = system.actorOf(MyBoundedActor.props, "boundedActor")
  boundedActor ! "fff"

  val log = system.actorOf(Props[Logger].withMailbox(""), "logger")
  log ! ""
}
