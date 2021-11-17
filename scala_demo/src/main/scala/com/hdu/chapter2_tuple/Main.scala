package com.hdu.chapter2_tuple

import scala.collection.mutable
import scala.collection.immutable

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/7/10
 * @Time 下午9:12
 */
object Main extends App {
  val pair = (99, "ssf")
  val mySet = Set("one", "two")
  val myMutableSet = mutable.Set("one", "two")
  val myImmutableSet = immutable.Set("one", "two")
  println(mySet)
  println(myMutableSet)
  println(myImmutableSet)
  println(pair._1)
}
