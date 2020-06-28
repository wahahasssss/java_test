package com.hdu.chapter5_class

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/7/15
  * @Time 下午2:49
  */
class Rational(n:Int,d:Int){
  require(d!=0,"分母不能为零")
  println("Created " + n + "/" + d)


  val number = n
  val denom = d

  def this(n:Int)  = this(n,1)

  override def toString: String = "n = " + n + ", d = " + d;

  def +(anther:Rational):Rational = new Rational(number*anther.denom+denom*anther.number,denom*anther.denom);

  def *(anther:Rational):Rational = new Rational(number*anther.number,denom*anther.denom)

  def *(num:Int):Rational = new Rational(num*number,denom)
}

object ClazzTest extends App {
  implicit def intToRational(n:Int) = new Rational(n)

  val c1 = new Rational(1,2);
  val c2 = new Rational(1,30);
  val oneRational = new Rational(1,2)
  val twoRational = new Rational(2,3)
  val result = oneRational + twoRational
  println(result)

  val thirdRation = new Rational(4)

  val result2 = oneRational * 10

  val result3 = 10 * oneRational


}
