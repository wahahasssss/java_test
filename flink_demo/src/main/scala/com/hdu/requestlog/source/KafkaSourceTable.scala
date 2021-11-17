package com.hdu.requestlog.source

import java.util.Properties

import com.hdu.requestlog.job.StudentsActivity
import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

/**
 * @author shushoufu
 * @date 2020/10/15
 **/
case class KafkaSourceTable() {
}

object KafkaSourceTable {
  val ip = "172.31.196.186"
  val properties = new Properties()
  properties.setProperty("bootstrap.servers", ip + ":9092," + ip + ":9093," + ip + ":9094")
  //  properties.setProperty("bootstrap.servers", "192.168.31.203:9092,192.168.31.203:9093,192.168.31.203:9094")
  properties.setProperty("group.id", "test")
  //  properties.setProperty("enable.auto.commit","false")
  //  properties.setProperty("auto-offset-reset","earliest")

  val topic = "TestComposeTopic";


  def getKafkaConsumer: FlinkKafkaConsumer[StudentsActivity] = {

    val kafkaConsumer: FlinkKafkaConsumer[StudentsActivity] = new FlinkKafkaConsumer[StudentsActivity](topic, new StudentActivitySchemaTable(), properties)
    kafkaConsumer
  }
}


