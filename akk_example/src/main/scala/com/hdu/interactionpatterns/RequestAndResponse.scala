package com.hdu.interactionpatterns

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, ActorSystem}

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/18
  * @Time 下午9:02
  */

object RequestAndResponse {

  case class Request(query:String,repondTo:ActorRef[Response])
  case class Response(result:String)
  val otherBehavior = Behaviors.receive[Request]{(ctx,msg)=>{
    msg match {
      case Request(query,repondTo)=>{
        repondTo ! Response("this is response")
        Behaviors.same
    }
    }
  }
  }

}

object Application1 extends App{
  import RequestAndResponse._
  var system = ActorSystem(otherBehavior,"reqandreq");
  val req:ActorRef[Request] = system
//  req ! Request("ffewf", )
}
