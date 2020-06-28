package com.hdu.chapter9_controlstructure

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/11/23
  * @Time 下午5:12
  */
object Main extends App {

  val map = Map.empty[String,Int]

  for(i <- 1 to 4){
    println(s"this number is $i")
  }



  def print(args:String*):Unit=for(arg<-args) println(s"args is $arg")



  print("one","two","three")
}
