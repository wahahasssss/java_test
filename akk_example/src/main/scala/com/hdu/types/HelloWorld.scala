package com.hdu.types

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午8:14
 */
object HelloWorld {

  final case class Greet(whom: String, replyTo: ActorRef[Greeted])

  final case class Greeted(whom: String, from: ActorRef[Greet])

  val greeter: Behavior[Greet] = Behaviors.receive { (ctx, msg) =>
    ctx.log.info("Hello {}", msg.whom)
    msg.replyTo ! Greeted(msg.whom, ctx.self)
    Behaviors.same
  }
}
