package com.hdu.chapter14_flink.datastream

import org.apache.commons.logging.impl.Log4JLogger
import org.apache.flink.streaming.api.scala._
import org.apache.flink.walkthrough.common.sink.AlertSink
import org.apache.flink.walkthrough.common.entity.Alert
import org.apache.flink.walkthrough.common.entity.Transaction
import org.apache.flink.walkthrough.common.source.TransactionSource
import org.slf4j.impl.Log4jLoggerFactory

/**
 * @author shushoufu
 * @date 2020/08/14
 **/
object FraudDetectionJob {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    print("begin FraudDetectionJob")
    for (args <- args) {

    }
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val transactions: DataStream[Transaction] = env
      .addSource(new TransactionSource)
      .name("transactions")

    val alerts: DataStream[MyAlert] = transactions
      .keyBy(transaction => transaction.getAccountId)
      .process(new FraudDetector)
      .name("fraud-detector")

    alerts
      .addSink(new MyAlertSink)
      .name("send-alerts")

    env.execute("Fraud Detection")
  }
}
