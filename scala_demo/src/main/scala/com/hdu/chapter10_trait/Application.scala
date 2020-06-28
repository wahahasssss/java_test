package com.hdu.chapter10_trait

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2019/5/7
  * @Time 5:42 PM
  */
object Application extends App {
  println("begin ....")
  val half = new OrderedRational(1,2)
  val third = new OrderedRational(1,3)
  println(half < third)
  println(half > third)
}
