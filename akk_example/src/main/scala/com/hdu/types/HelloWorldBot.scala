package com.hdu.types

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午8:22
 */
object HelloWorldBot {
  def bot(greetingCounter: Int, max: Int): Behavior[HelloWorld.Greeted] = {
    Behaviors.receive { (ctx, msg) => {
      val n = greetingCounter + 1;
      ctx.log.info("Greeting {} for {}", n, msg.whom)
      if (n == max) {
        Behaviors.stopped
      } else {
        msg.from ! HelloWorld.Greet(msg.whom, ctx.self)
        bot(n, max)
      }
    }
    }
  }
}
