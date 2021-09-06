package com.hdu.requestlog.job

import java.util

import com.hdu.requestlog.model.StudentsActivityInfo
import com.hdu.requestlog.sink.{Kafka2EsSink, PrintSink, PrintSinkWithType}
import org.apache.flink.api.scala._
import com.hdu.requestlog.source.KafkaSource
import com.hdu.requestlog.watermark.CustomWaterStrategy
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

import org.apache.flink.streaming.api.windowing.assigners.{SlidingEventTimeWindows, SlidingProcessingTimeWindows, TumblingEventTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time

import org.apache.http.HttpHost

/**
 * @author shushoufu
 * @date 2020/10/19
 **/
case class KafkaConsumerJob(private val esNode:String,
                            private val esPort:Int){

  def startJob:Unit={
    print("begin FraudDetectionJob")

    val env:StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.getConfig.setAutoWatermarkInterval(100L)
    env.enableCheckpointing(10000L)
    env.setStateBackend(new FsStateBackend("file:///opt/flink/checkpoints"))



    // 任务取消时会保留检查点
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    env.setParallelism(1)

    val stream : DataStream[StudentsActivityInfo] = env.addSource(KafkaSource.getKafkaConsumer)
      .assignTimestampsAndWatermarks(CustomWaterStrategy.initWaterStrategy())
      .name("kafka_source")

//
//    val detectorStream = stream
//      .keyBy((k) => k.name)
//      .process(new KafkaDetector)
//      .setParallelism(3)
//      .name("flinProcess")
//
////    val hosts = buildEsNodes
////    val builder = new ElasticsearchSink.Builder(hosts,Kafka2EsSink.initToEsSink)
////
////    val sinFunction = builder.build
//
//    val printSink = new PrintSink
//
//    detectorStream.addSink(printSink).name("kafka_print_sink")


    val windowsStream = stream.keyBy(s=>s.name)
      .window(SlidingEventTimeWindows.of(Time.seconds(5), Time.seconds(3)))
      .reduce((s1,s2)=>StudentsActivityInfo(s1.name,s1.age + s2.age,"",s1.activityTime, s1.activity,s1.activityTimestamp))


    windowsStream.addSink(new PrintSinkWithType[StudentsActivityInfo]("sliding windows")).name("print_sink")

    val tStream = stream.keyBy(s=>s.name)
      .window(TumblingEventTimeWindows.of(Time.seconds(10), Time.seconds(3)))
      .reduce((s1,s2)=>StudentsActivityInfo(s1.name,s1.age + s2.age,"",s1.activityTime, s1.activity,s1.activityTimestamp))

    tStream.addSink(new PrintSinkWithType[StudentsActivityInfo]("tumbling windows")).name("print_t_windows_sink")
    env.execute("kafka_stream_job")
  }

  private def buildEsNodes: util.List[HttpHost] = {
    val hosts: util.List[HttpHost] = new util.ArrayList[HttpHost]
    val nodes: Array[String] = esNode.split(",")
    if (nodes == null || nodes.length == 0 || esPort == null) throw new Exception("es节点信息错误")
    for (node <- nodes) {
      hosts.add(new HttpHost(node, esPort, "http"))
    }
    hosts
  }
}

object KafkaConsumerJob{
  private val esNode = "127.0.0.1"
  private val esPort = 9200
  private val consumerJob = KafkaConsumerJob(esNode, esPort)
  def start:Unit={
    consumerJob.startJob
  }


}
