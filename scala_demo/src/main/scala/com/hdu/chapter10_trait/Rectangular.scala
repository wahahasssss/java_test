package com.hdu.chapter10_trait

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/12/25
  * @Time 下午7:04
  */
class Point(val x:Int,val y:Int)
trait Rectangular {
  def topLeft:Point
  def bottomRight:Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}

