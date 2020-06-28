package com.hdu

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/4/17
  * @Time 上午10:12
  */


case class Test(){
  def factorial(x: BigInt):BigInt={
    if(x == 0)
      return 1
    return x * factorial(x - 1)
  }
}

object Main extends App {
  println("akka demo running begin...")
  var test = Test()
  println("the factorial result is " + test.factorial(30) )

}
