package com.hdu.actors

import akka.actor.Actor
import akka.actor.Stash

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/10
  * @Time 下午6:10
  */
class ActorWithProtocol extends Actor with Stash {
  override def receive: Receive = {
    case "open" =>{
      println("the action is open...")
      unstashAll()
      context.become({
        case "write"=>{
          println("write somethin")
        }
        case "close"=>{
          unstashAll()
          context.unbecome()
        }
          case msg => stash()
      },discardOld = false)
    }
    case msg => stash()
  }
}
