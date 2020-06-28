package com.hdu.chapter8_classAndObject

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/11/23
  * @Time 下午4:46
  */
class Rational(n:Int,d:Int){
  require(d != 0)
  private val g = gcd(n.abs,d.abs);
  val number = n/g;
  val denom = d/g;
  def this(n:Int) = this(n,1)

  def + (that:Rational):Rational={
    new Rational(
      number * that.denom + that.number * denom,
      denom * that.denom
    )
  }



  def + (i:Int):Rational = new Rational(number + i * denom,denom)

  def - (that:Rational) : Rational = new Rational(
    number * that.denom - that.number * denom,
    denom * that.denom
  )


  override def toString: String = number + "/" + denom

  private def gcd(a:Int,b:Int):Int=  if(b==0) a else gcd(b,a%b)
}
