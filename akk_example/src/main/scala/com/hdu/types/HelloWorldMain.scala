package com.hdu.types

import akka.actor.typed.Behavior
import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem, Behavior, Terminated}
import com.typesafe.config.ConfigFactory

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午8:27
 */
object HelloWorldMain extends App {

  final case class Start(name: String)

  val main: Behavior[Start] = {
    Behaviors.setup { context =>
      val greeter = context.spawn(HelloWorld.greeter, "greeter")
      Behaviors.receiveMessage { msg => {
        val replyTo = context.spawn(HelloWorldBot.bot(greetingCounter = 0, max = 3), msg.name)
        greeter ! HelloWorld.Greet(msg.name, replyTo)
        Behaviors.same
      }
      }
    }
  }

  val system: ActorSystem[HelloWorldMain.Start] = ActorSystem(HelloWorldMain.main, "hello", ConfigFactory.load("application1.conf"))

  system ! HelloWorldMain.Start("World")
  system ! HelloWorldMain.Start("akka")
}
