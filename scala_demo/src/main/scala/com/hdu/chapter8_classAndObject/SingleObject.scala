package com.hdu.chapter8_classAndObject

import scala.collection.mutable

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/11/23
  * @Time 下午4:35
  */
object SingleObject {
  private val cache = mutable.Map.empty[String,Int]

  def print(): Unit ={
    println(cache.toString())
  }
}
