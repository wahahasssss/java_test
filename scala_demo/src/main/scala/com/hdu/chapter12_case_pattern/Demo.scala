package com.hdu.chapter12_case_pattern

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/1/30
 * @Time 下午5:59
 */

abstract class Expr

case class Var(name: String) extends Expr

case class Number(name: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Demo extends App {
  println("begin demo run...")
  var binOp = BinOp("+", Number(1), Var("x"))
  println(binOp)
}