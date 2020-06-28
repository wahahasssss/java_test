package com.hdu.chapter10_trait

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/12/25
  * @Time 下午7:07
  */
class Rectangle(val topLeft:Point,val bottomRight:Point) extends Rectangular {

}



object Mainone extends App{
  println("begin ...")
  var rectangle = new Rectangle(new Point(10,10),new Point(20,20))
  println(rectangle.left)
  println(rectangle.right)
  println(rectangle.width)
}