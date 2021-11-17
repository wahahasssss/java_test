package com.hdu.fsm

import akka.actor.{ActorRef, ActorSystem, FSM, Props}
import akka.util.ByteString
import com.hdu.DemoActor

import scala.collection.immutable
import scala.concurrent.duration._

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/12
 * @Time 下午6:47
 */

final case class SetTarget(ref: ActorRef)

final case class Queue(obj: Any)

case object Flush

final case class Batch(obj: immutable.Seq[Any])

sealed trait State

case object Idle extends State

case object Active extends State

sealed trait Data

case object Uninitialized extends Data

final case class Todo(target: ActorRef, queue: immutable.Seq[Any]) extends Data

class Buncher extends FSM[State, Data] {
  startWith(Idle, Uninitialized)

  when(Idle) {
    case Event(SetTarget(ref), Uninitialized) => {
      stay using Todo(ref, Vector.empty)
    }
  }

  onTransition {
    case Active -> Idle => {
      stateData match {
        case Todo(ref, queue) => ref ! Batch(queue)
        case _ => println("do nothing")
      }
    }
  }

  when(Active, stateTimeout = 1 second) {
    case Event(Flush | StateTimeout, t: Todo) => {
      goto(Idle) using t.copy(queue = Vector.empty)
    }
  }

  whenUnhandled {
    case Event(Queue(obj), t@Todo(_, v)) => {
      goto(Active) using t.copy(queue = v :+ obj)
    }
    case Event(e, s) => {
      log.warning("received unhandled request {} in state {}/ {}", e, stateName, s)
      stay()
    }
  }
  initialize()
}

object DemoCode {

  trait StateType

  case object SomeState extends StateType

  case object Processing extends StateType

  case object Error extends StateType

  case object Idle extends StateType

  case object Active extends StateType


  class Dummy extends FSM[StateType, Int] {

    class X

    val newData = 42

    object WillDo

    object Tick

    when(SomeState) {
      case Event(msg, _) => {
        goto(Processing) using (newData) forMax (5 seconds) replying (WillDo)
      }
    }

    onTransition {
      case Idle -> Active => setTimer("timeout", Tick, 1 second, repeat = true)
      case Active -> _ => cancelTimer("timeout")
      case x -> Idle => log.info("entering Idle from " + x)
    }

    onTransition(handler _)


    def handler(from: StateType, to: StateType): Unit = {
      println("handle handle handle is do something...")
    }

    when(Error) {
      case Event("stop", _) => {
        println("stop ...")
        stop()
      }
    }
    when(SomeState)(transform {
      case Event(bytes: ByteString, read) ⇒ stay using (read + bytes.length)
    } using {
      case s@FSM.State(state, read, timeout, stopReason, replies) if read > 1000 ⇒
        goto(Processing)
    })

    val processingTrigger: PartialFunction[State, State] = {
      case s@FSM.State(state, read, timeout, stopReason, replies) if read > 1000 ⇒
        goto(Processing)
    }

    when(SomeState)(transform {
      case Event(bytes: ByteString, read) ⇒ stay using (read + bytes.length)
    } using processingTrigger)

    onTermination {
      case StopEvent(FSM.Normal, state, data) ⇒ // ...
      case StopEvent(FSM.Shutdown, state, data) ⇒ // ...
      case StopEvent(FSM.Failure(cause), state, data) ⇒ // ...
    }

    whenUnhandled {
      case Event(x: X, data) ⇒
        log.info("Received unhandled event: " + x)
        stay
      case Event(msg, _) ⇒
        log.warning("Received unknown event: " + msg)
        goto(Error)
    }

  }

}

object main extends App {
  var system = ActorSystem("fsm_system")
  val buncher = system.actorOf(Props[Buncher], "buncher")
  var testActor = system.actorOf(DemoActor.props("demo"), "mm")
  buncher ! SetTarget(testActor)
  buncher ! Queue(42)

}