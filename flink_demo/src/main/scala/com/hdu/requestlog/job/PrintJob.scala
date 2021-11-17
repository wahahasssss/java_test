package com.hdu.chapter14_flink.datastream

import com.hdu.requestlog.detecotors.FraudDetector
import org.apache.commons.logging.impl.Log4JLogger
import org.apache.flink.streaming.api.scala._
import org.apache.flink.walkthrough.common.sink.AlertSink
import org.apache.flink.walkthrough.common.entity.Alert
import org.apache.flink.walkthrough.common.entity.Transaction
import org.apache.flink.walkthrough.common.source.TransactionSource

/**
 * @author shushoufu
 * @date 2020/08/14
 **/

case class PrintJob() {

}

object PrintJob {
  @throws[Exception]
  def start(): Unit = {
    print("begin FraudDetectionJob")

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val transactions: DataStream[Transaction] = env
      .addSource(new TransactionSource)
      .name("transactions")

    val alerts: DataStream[Alert] = transactions
      .keyBy(transaction => transaction.getAccountId)
      .process(new FraudDetector())
      .name("fraud-detector")

    alerts
      .addSink(new AlertSink)
      .name("send-alerts")

    env.execute("Fraud Detection")
  }
}
