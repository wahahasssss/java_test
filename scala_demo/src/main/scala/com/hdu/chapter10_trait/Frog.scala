package com.hdu.chapter10_trait

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/12/25
  * @Time 下午5:34
  */

class Animal{}

trait HasLegs extends Animal
trait Furry extends Animal
trait FourLegged extends HasLegs
class Cat extends Animal with Furry with FourLegged

class Frog extends Animal with Philosophical  with HasLegs {
  override def toString = "green";
}

object Frog extends App{
  var frog = new Frog()
  frog.philosophical()

}
