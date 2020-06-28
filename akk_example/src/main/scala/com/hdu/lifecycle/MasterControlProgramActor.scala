//import akka.actor.typed.scaladsl.Behaviors
//import akka.actor.typed.{ ActorSystem, Logger, PostStop }
//import akka.testkit.typed.scaladsl.ActorTestKit
//
//import scala.concurrent.Await
//import scala.concurrent.duration._
//
//object MasterControlProgramActor {
//  sealed trait JobControlLanguage
//  final case class SpawnJob(name: String) extends JobControlLanguage
//  final case object GracefulShutdown extends JobControlLanguage
//
//  // Predefined cleanup operation
//  def cleanup(log: Logger): Unit = log.info("Cleaning up!")
//
//  val mcpa = Behaviors.receive[JobControlLanguage] { (ctx, msg) ⇒
//    msg match {
//      case SpawnJob(jobName) ⇒
//        ctx.log.info("Spawning job {}!", jobName)
//        ctx.spawn(Job.job(jobName), name = jobName)
//        Behaviors.same
//      case GracefulShutdown ⇒
//        ctx.log.info("Initiating graceful shutdown...")
//        // perform graceful stop, executing cleanup before final system termination
//        // behavior executing cleanup is passed as a parameter to Actor.stopped
//        Behaviors.stopped {
//          Behaviors.receiveSignal {
//            case (context, PostStop) ⇒
//              cleanup(context.system.log)
//              Behaviors.same
//          }
//        }
//    }
//  }.receiveSignal {
//    case (ctx, PostStop) ⇒
//      ctx.log.info("MCPA stopped")
//      Behaviors.same
//  }
//}
//
//object Job {
//  import GracefulStopDocSpec.MasterControlProgramActor.JobControlLanguage
//
//  def job(name: String) = Behaviors.receiveSignal[JobControlLanguage] {
//    case (ctx, PostStop) ⇒
//      ctx.log.info("Worker {} stopped", name)
//      Behaviors.same
//  }
//}
//
//import MasterControlProgramActor._
//
//val system: ActorSystem[JobControlLanguage] = ActorSystem(mcpa, "B7700")
//
//system ! SpawnJob("a")
//system ! SpawnJob("b")
//
//Thread.sleep(100)
//
//// gracefully stop the system
//system ! GracefulShutdown
//
//Thread.sleep(100)
//
//Await.result(system.whenTerminated, 3.seconds)