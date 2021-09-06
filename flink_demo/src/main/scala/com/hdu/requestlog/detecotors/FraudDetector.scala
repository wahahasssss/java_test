package com.hdu.requestlog.detecotors

import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.util.Collector
import org.apache.flink.walkthrough.common.entity.{Alert, Transaction}

/**
 * @author shushoufu
 * @date 2020/10/15
 **/
object FraudDetector{
  val SMALL_AMOUNT: Double = 1.00
  val LARGE_AMOUNT: Double = 500.00
  val ONE_MINUTE: Long     = 60 * 1000L
}

@SerialVersionUID(1L)
class FraudDetector extends KeyedProcessFunction[Long, Transaction, Alert]{
  @throws[Exception]
  def processElement(i: Transaction,
                     context: KeyedProcessFunction[Long, Transaction, Alert]#Context,
                     collector: Collector[Alert]): Unit ={
    val myAlert = new Alert()
    collector.collect(myAlert);
  }
}
