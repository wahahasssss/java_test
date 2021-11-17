package com.hdu.route

import akka.actor.{ActorSystem, Address, AddressFromURIString, Props}
import akka.remote.WireFormats.RemoteRouterConfig
import akka.remote.routing.RemoteRouterConfig
import akka.routing.RoundRobinPool
import com.typesafe.config.ConfigFactory

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/12
 * @Time 上午9:38
 */
object RouteMain extends App {


  var config = ConfigFactory.load("application1")
  val system: ActorSystem = ActorSystem("routesystem", config)
  val adresses = Seq(
    Address("akka.tcp", "remotesys", "host", 1234),
    AddressFromURIString("akka.tcp://othersys@host:port")
  )

  //  var routerReomote = system.actorOf(RemoteRouterConfig(RoundRobinPool(5),adresses).props(Props[Echo]))
}
