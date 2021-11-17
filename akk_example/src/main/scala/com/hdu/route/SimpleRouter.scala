package com.hdu.route

import akka.actor.{Actor, ActorRef, Props, Terminated}
import akka.routing
import akka.routing._
import com.hdu.actors.MyActor

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 下午8:56
 */
class SimpleRouter extends Actor {

  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(MyActor.props)
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  val router1: ActorRef = context.actorOf(FromConfig.props(Props[MyActor]), "router1")
  val router2 = context.actorOf(routing.RoundRobinPool(5).props(Props[MyActor]), "router2")

  override def receive: Receive = {
    case w: MyActor => router.route(w, sender())
    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(MyActor.props)
      context watch r
      router = router.addRoutee(r)
  }

}
