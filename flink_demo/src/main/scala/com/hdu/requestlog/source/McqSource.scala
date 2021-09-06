//package com.hdu.requestlog.source
//
//import java.nio.charset.StandardCharsets
//import java.util.Properties
//
//import com.hdu.requestlog.utils.PropertiesUtils
//import org.apache.flink.api.common.typeinfo.TypeInformation
//
//
///**
// * @author shushoufu
// * @date 2020/08/27
// **/
//class McqStringValueDeserializationSchema extends KeyValueDeserializationSchema[String] {
//  private var timeStamp = System.currentTimeMillis
//
//  override def deserializeKeyAndValue(key: Array[Byte], value: Array[Byte]): String = {
//    if (System.currentTimeMillis - timeStamp >= 5000) {
//      System.gc()
//      timeStamp = System.currentTimeMillis
//    }
//    if (value != null) return new String(value, StandardCharsets.UTF_8)
//    null
//  }
//
//  override def getProducedType: TypeInformation[String] = TypeInformation.of(classOf[String])
//}
//object McqSource{
//  private val properties:Properties = PropertiesUtils.loadProperties();
//  private val mcqProperties:Properties = new Properties();
//  mcqProperties.setProperty(RocketMQConfig.NAME_SERVER_ADDR, properties.getProperty("mcq.name.server"))
//  mcqProperties.setProperty(RocketMQConfig.CONSUMER_GROUP, properties.getProperty("system.key"))
//  mcqProperties.setProperty(RocketMQConfig.CONSUMER_TOPIC, properties.getProperty("mcq.ext.request.log"))
//  mcqProperties.setProperty(RocketMQConfig.CONSUMER_TAG, properties.getProperty("mcq.ext.request.log.tags"))
//  private val source: RocketMQSource[String] = new RocketMQSource[String](new McqStringValueDeserializationSchema(), mcqProperties);
//
//
//  /**
//   * 获取mcq source
//   * @return
//   */
//  def getMcqSource():RocketMQSource[String] = {
//    source
//  }
//}
