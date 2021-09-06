package com.hdu.requestlog.job

import com.hdu.requestlog.model.{StudentsActivityInfo, SumJavaInfo}
import com.hdu.requestlog.sink.PrintSinkWithTable
import com.hdu.requestlog.source.{KafkaSource, KafkaSourceTable}
import com.hdu.requestlog.watermark.{CustomWaterStrategy, CustomWaterStrategyTable}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._
import org.apache.flink.runtime.state.filesystem.FsStateBackend
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala._
import org.apache.flink.types.Row


/**
 * table接口任务
 * @author shushoufu
 * @date 2020/11/10
 **/
case class SumInfo(private var name : String,var age:BigInt)
case class StudentsActivity(var name: String,
                                var age: Int,
                                var birthday: String,
                                var activityTime: String,
                                var activity: String,
                                var activityTimestamp: Long){
  def this() {
    this("",0,"","","",0L)
  }
}
case class KafkaConsumerTableJob(){
  def startJob(): Unit ={

    //初始化环境设置
    val envSetting = EnvironmentSettings.newInstance()
      .useBlinkPlanner()
      .inStreamingMode()
      .build();

    //设置流环境的配置信息，包括窗口，状态后端
    val bsSteamingEnv = StreamExecutionEnvironment.getExecutionEnvironment
    bsSteamingEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    bsSteamingEnv.getConfig.setAutoWatermarkInterval(100L)
    bsSteamingEnv.enableCheckpointing(10000L)
    bsSteamingEnv.setStateBackend(new FsStateBackend("file:///opt/flink/checkpoints"))

    // 任务取消时会保留检查点
    bsSteamingEnv.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION)
    bsSteamingEnv.setParallelism(1)
    bsSteamingEnv.registerType(classOf[Row])
    bsSteamingEnv.registerType(classOf[StudentsActivityInfo])
    val bsTableEnv = StreamTableEnvironment.create(bsSteamingEnv, envSetting)


    //接入外部 source kafka
    val stream : DataStream[StudentsActivity] = bsSteamingEnv.addSource(KafkaSourceTable.getKafkaConsumer)
      .assignTimestampsAndWatermarks(CustomWaterStrategyTable.initWaterStrategy())
      .name("kafka_source")


    bsTableEnv.createTemporaryView("students_info", stream, $"name",$"age",$"birthday",$"activityTime",$"activity",$"activityTimestamp")


    // 从视图students_info 查询信息
    val table : Table = bsTableEnv.sqlQuery("SELECT name, age, birthday, activityTime,activity,activityTimestamp from students_info")


    //将查询接过转成stream

    //todo 待完成
    val retractStream = bsTableEnv.toRetractStream[StudentsActivityInfo](table)
//    val appendStream = bsTableEnv.toAppendStream[StudentsActivityInfo](table);
//
//
//    retractStream.addSink(new PrintSinkWithTable())
//      .name("allOrderSink")
//      .uid("allOrderSink")
//      .setParallelism(1)


    bsSteamingEnv.execute("kafka job execute")

  }
}

object KafkaConsumerTableJob {
  private val kafkaConsumerTableJob = KafkaConsumerTableJob()
  def start(): Unit ={
    kafkaConsumerTableJob.startJob()
  }
}
