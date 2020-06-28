package com.hdu

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.hdu.actors.{ActorWithProtocol, MyActor}

import scala.concurrent.duration._
/**FlushOnShutdown.Timeout
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/2
  * @Time 下午7:44
  */
object Main{
  def main(args: Array[String]): Unit = {
    implicit val timeout = Timeout(5 seconds)
    System.out.println("begin akka sssssss...")
    var actorSystem = ActorSystem("actorsystem")
    var actorRef = actorSystem.actorOf(DemoActor.props("ssf"),"demoActor")
    actorRef ! "hhh"
    actorRef ! "next"
    val future = actorRef ? "future"

    var myActor = actorSystem.actorOf(MyActor.props)
//    myActor.tell(MyActor.FirstTick,null)


    var protocolActor = actorSystem.actorOf(Props[ActorWithProtocol],"protocolActor")
    protocolActor.tell("open",null)
    protocolActor.tell("write",null)
  }
}
