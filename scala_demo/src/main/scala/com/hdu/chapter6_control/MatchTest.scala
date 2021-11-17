package com.hdu.chapter6_control

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/15
 * @Time 下午4:46
 */
object MatchTest extends App {
  val value = "hello"

  value match {
    case "hello" => println("someone say hello to you")
    case "bye" => println("someone say goodbye to you")
    case _ => println("nothing to do")
  }


  for (i <- 1 to 100) {
    println("this is " + i)
  }
}
