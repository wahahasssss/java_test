//package com.hdu
//
//import akka.actor.{ActorRef, ActorSystem, Props}
//import com.typesafe.config.{Config, ConfigFactory}
//import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
//import akka.testkit.{EventFilter, ImplicitSender, TestActors, TestKit}
//import com.hdu.supervisor.{ChildActor, SupervisorActor}
//
//
///**
//  * DESCRIPTION:
//  *
//  * @author shushoufu
//  * @Date 2018/4/10
//  * @Time 下午8:49
//  */
//class FaultHandlingDocSpec(_system:ActorSystem) extends TestKit(_system) with ImplicitSender with WordSpecLike
// with Matchers with BeforeAndAfterAll {
//  def this() = this(ActorSystem(
//    "FaultHandlingDocSpec",ConfigFactory.parseString("""
//      akka {
//        loggers = ["akka.testkit.TestEventListener"]
//        loglevel = "WARNING"
//      }
//      """)
//  ))
//
//  override def afterAll(): Unit = {
//    TestKit.shutdownActorSystem(system)
//  }
//
//
//  val supervisor = system.actorOf(Props[SupervisorActor],"supervisor")
//  supervisor ! Props[ChildActor]
//
//  val child = expectMsgType[ActorRef]
//
//  child ! 42
//  child ! "get"
//  expectMsg(42)
//
//  child ! new ArithmeticException
//  child ! "get"
//  expectMsg(42)
//
//  child ! new NullPointerException
//  child ! "get"
//  expectMsg(0)
//
//}
