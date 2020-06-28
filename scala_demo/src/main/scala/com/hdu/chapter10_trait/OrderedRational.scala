package com.hdu.chapter10_trait

/**
  * DESCRIPTION:
  *
  * @author shushoufu
  * @Date 2018/12/25
  * @Time 下午7:19
  */
class OrderedRational(val n :Int,val m:Int) extends Ordered[OrderedRational]{
  override def compare(that: OrderedRational): Int = (this.n / this.m) - (that.n / that.m)
}