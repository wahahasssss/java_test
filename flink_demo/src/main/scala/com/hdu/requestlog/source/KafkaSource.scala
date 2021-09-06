package com.hdu.requestlog.source

import java.time.Duration
import java.util.Properties

import com.hdu.requestlog.model.StudentsActivityInfo
import com.hdu.requestlog.watermark.{CustomTimestampAssignerSupplier, CustomWaterStrategy}
import org.apache.flink.api.common.eventtime.{TimestampAssigner, TimestampAssignerSupplier, WatermarkStrategy}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
 * @author shushoufu
 * @date 2020/10/15
 **/
case class KafkaSource() {
}

object KafkaSource{
  val ip = "172.31.196.186"
  val properties = new Properties()
  properties.setProperty("bootstrap.servers",ip + ":9092," + ip + ":9093," + ip + ":9094")
//  properties.setProperty("bootstrap.servers", "192.168.31.203:9092,192.168.31.203:9093,192.168.31.203:9094")
  properties.setProperty("group.id", "test")
//  properties.setProperty("enable.auto.commit","false")
//  properties.setProperty("auto-offset-reset","earliest")

  val topic = "TestComposeTopic";


  def getKafkaConsumer:FlinkKafkaConsumer[StudentsActivityInfo] = {

    val kafkaConsumer : FlinkKafkaConsumer[StudentsActivityInfo] = new FlinkKafkaConsumer[StudentsActivityInfo](topic, new StudentActivitySchema(), properties)
    kafkaConsumer
  }
}
