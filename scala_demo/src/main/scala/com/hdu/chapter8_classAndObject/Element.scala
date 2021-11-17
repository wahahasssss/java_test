package com.hdu.chapter8_classAndObject

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/11/23
 * @Time 下午5:32
 */
abstract class Element {
  def content: Array[String]


  val height: Int = content.length

  val width: Int = if (height == 0) 0 else content(0).length


}
