package com.hdu

import akka.actor.{AbstractActor, Actor, Props}
import akka.japi.pf.ReceiveBuilder
import com.hdu.actors.SecondActor

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/3
  * @Time 下午5:37
  */
object DemoActor{
  def props(msg:String):Props = Props(new DemoActor(msg))
}

class DemoActor(msg:String) extends Actor {
  var nextActor = context.actorOf(SecondActor.props(msg),"secondActor")
  override def receive: Receive = {
    case "hhh" =>
      println(String.format("receive msg is %s,hhh",msg))
    case "next" =>
      nextActor.tell("bob",self)
    case _ =>
      Thread.sleep(10000)
      System.out.println("===========>>>>>>>>>>>>>>>>")
  }
}
