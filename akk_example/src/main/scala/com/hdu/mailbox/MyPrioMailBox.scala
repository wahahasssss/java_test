package com.hdu.mailbox

import akka.actor.ActorSystem
import akka.dispatch.{PriorityGenerator, UnboundedStablePriorityMailbox}
import com.typesafe.config.Config

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/11
 * @Time 下午8:36
 */
class MyPrioMailBox(setting: ActorSystem.Settings, config: Config) extends UnboundedStablePriorityMailbox(PriorityGenerator {
  // 'highpriority messages should be treated first if possible
  case 'highpriority ⇒ 0

  // 'lowpriority messages should be treated last if possible
  case 'lowpriority ⇒ 2

  // We default to 1, which is in between high and low
  case otherwise ⇒ 1
}) {

}
