package com.hdu.chapter4_string

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/7/15
  * @Time 下午2:20
  */
object StringTest extends App {
  val name = "asdf"
  val s1 = s"oneoneone欧尼， $name"
  val index = s1 indexOf '0'
  println(s1)
  println(index)
}
