package com.hdu.interactionpatterns

import akka.NotUsed
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ ActorRef, ActorSystem, Behavior, Terminated }

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/18
  * @Time 下午8:46
  */


object InterAction {

  case class PrintMe(message:String)
  val printerBehavior:Behavior[PrintMe] = Behaviors.receive{
    case (ctx,PrintMe(message)) =>{
      ctx.log.info(message)
      Behaviors.same
    }
  }
}


object Application extends App{
  import InterAction._
  val system = ActorSystem(printerBehavior,"fire-and-forget-sample")
  val printer:ActorRef[PrintMe] = system
  printer ! PrintMe("message 1")
  printer ! PrintMe("not message 2")


}
