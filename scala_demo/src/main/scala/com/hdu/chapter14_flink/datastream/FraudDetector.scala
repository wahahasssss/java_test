package com.hdu.chapter14_flink.datastream

import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import org.apache.flink.walkthrough.common.entity.{Alert, Transaction}

/**
 * @author shushoufu
 * @date 2020/08/14
 **/

object FraudDetector{
  val SMALL_AMOUNT: Double = 1.00
  val LARGE_AMOUNT: Double = 500.00
  val ONE_MINUTE: Long     = 60 * 1000L
}

@SerialVersionUID(1L)
class FraudDetector extends KeyedProcessFunction[Long, Transaction, MyAlert]{
  @throws[Exception]
  def processElement(i: Transaction,
                     context: KeyedProcessFunction[Long, Transaction, MyAlert]#Context,
                     collector: Collector[MyAlert]): Unit ={
    val myAlert = new MyAlert(i.getAccountId, i.getAmount.toString)
    collector.collect(myAlert);
  }
}
