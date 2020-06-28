package com.hdu.actors

import akka.actor.{Actor, Props}

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/9
  * @Time 下午3:56
  */
object SecondActor{
  def props(name:String):Props = Props(classOf[SecondActor],name);
}

class SecondActor(name:String) extends Actor{

  override def preStart(): Unit = {
    println(s"the second actor is preStart, path is ${self.path}")
  }


  override def postStop(): Unit = {
    println(s"the second actor is postStop, path is ${self.path}")
  }

  override def receive: Receive = {
    case _ => println(String.format("this name is %s,sender path is %s",name,sender().path))
  }
}
