package com.hdu.requestlog.job

import java.util
import java.util.{ArrayList, List, Properties}

import com.hdu.requestlog.detecotors.RcsFlinLogStreamDetector
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.http.HttpHost
import org.slf4j.LoggerFactory
import com.hdu.requestlog.utils.PropertiesUtils

/**
 * @author shushoufu
 * @date 2020/08/27
 **/
case class RequestLogJob(private val esNode:String,
                         private val esPort:Int,
                         private val esBulkFlushInterval:Int,
                         private val esBulkFlushMaxSizeMb:Int,
                         private val esBulkFlushMaxActions:Int,
                         private val consumerParallelism:Int,
                         private val sinkParallelism:Int,
                         private val rcsFlinLogStreamDetector: RcsFlinLogStreamDetector
                   ) {
  @throws[Exception]
  private final val logger = LoggerFactory.getLogger(this.getClass)
  def start(): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    env.enableCheckpointing(10 * 30 * 1000, CheckpointingMode.AT_LEAST_ONCE)
//    env.getCheckpointConfig.setCheckpointTimeout(30 *  60 * 1000)

//    val mcqDataStream = env
//      .addSource(mcqSource)
//      .setParallelism(consumerParallelism)
//      .name("mcqSource")
//    val logToDataStream = mcqDataStream
//      .keyBy((k: String) => k)
//      .process(rcsFlinLogStreamDetector)
//      .setParallelism(consumerParallelism)
//      .name("flinProcess")
//    val hosts = buildEsNodes
//    val builder = new ElasticsearchSink.Builder(hosts,FlinToEsSink.getEsSinkInstance())
//    builder.setBulkFlushInterval(esBulkFlushInterval);
//    builder.setBulkFlushMaxActions(esBulkFlushMaxActions)
//    builder.setBulkFlushMaxSizeMb(esBulkFlushMaxSizeMb)
//    builder.setFailureHandler(new EsFailureHandler)
//    //builder.setRestClientFactory(restClientFactory);
//    val sinFunction = builder.build
//    logToDataStream
//      .addSink(sinFunction)
//      .setParallelism(sinkParallelism)
//      .name("esSink")
    env.execute("flin2Es")
  }

  private def buildEsNodes: util.List[HttpHost] = {
    val hosts: util.List[HttpHost] = new util.ArrayList[HttpHost]
    val nodes: Array[String] = esNode.split(",")
    if (nodes == null || nodes.length == 0 || esPort == null) throw new Exception("es节点信息错误")
    for (node <- nodes) {
      hosts.add(new HttpHost(node, esPort, "http"))
    }
    logger.info("init es node ,{}", hosts)
    hosts
  }
}


object RequestLogJob{
  private val properties:Properties = PropertiesUtils.loadProperties()
  private val esNodes = properties.getProperty("es.node")
  private val esPort = properties.getProperty("es.port").toInt
  private val flushMaxAction = properties.getProperty("es.bulk.flush.max.actions").toInt
  private val flushMaxSizeMb = properties.getProperty("es.bulk.flush.max.size.mb").toInt
  private val flushIntervalMs = properties.getProperty("es.bulk.flush.interval.ms").toInt
  private val consumerParallelism = properties.getProperty("flin.consumer.parallelism").toInt
  private val sinkParallelism = properties.getProperty("flin.sink.parallelism").toInt
  private val rcsFlinLogStreamDetector = new RcsFlinLogStreamDetector
  private val job = RequestLogJob(
    esNode = esNodes,
    esPort = esPort,
    esBulkFlushInterval = flushIntervalMs,
    esBulkFlushMaxSizeMb = flushMaxSizeMb,
    esBulkFlushMaxActions = flushMaxAction,
    rcsFlinLogStreamDetector = rcsFlinLogStreamDetector,
    consumerParallelism = consumerParallelism,
    sinkParallelism = sinkParallelism
  )
  def start():Unit = {
    job.start()
  }
}
