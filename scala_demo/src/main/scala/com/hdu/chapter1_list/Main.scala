package com.hdu.chapter1_list

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/7/6
  * @Time 上午10:32
  */
object Main extends App {
  println("------------begin---------------")
  val numbers = Array("zero","one","two")
  val numbersList = List(2,3)
  val numberList2 = 1::numbersList
  println(numberList2)

  val thrill = "Will"::"fill"::"until"::Nil

  println(thrill)

}
