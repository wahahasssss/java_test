package com.hdu.tree

import akka.actor.ActorSystem

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/22
 * @Time 上午11:22
 */
object TreeMain extends App {
  println("tree test start...")
  var system = ActorSystem.create("tres-test")
  var firstActor = system.actorOf(TreeFirstActor.props("first"))
  var createSecond = CreateSecond
  firstActor ! createSecond
}
