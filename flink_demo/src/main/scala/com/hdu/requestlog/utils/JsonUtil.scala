package com.hdu.requestlog.utils

import java.io.IOException
import java.text.SimpleDateFormat

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

/**
 * @author shushoufu
 * @date 2020/08/26
 **/
object JsonUtil {
  final val objectMapper = new ObjectMapper();
  final val STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss"
  final val logger = LoggerFactory.getLogger(this.getClass)
  //对象的所有字段全部列入
  objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
  //取消默认转换timestamps形式
  objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
  //忽略空Bean转json的错误
  objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
  //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
  objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT))
  //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  objectMapper.registerModule(DefaultScalaModule)


  /**
   * 序列化
   * @param obj
   * @return
   */
  def toJsonString(obj: Any): String = {
    try{
      val jsonStr = objectMapper.writeValueAsString(obj)
      jsonStr
    } catch {
      case e: IOException =>
        logger.error("Parse String to Object error : {}" + e.getMessage)
        ""
    }
  }

  /**
   * 反序列化
   * @param str
   * @param clazz
   * @tparam T
   * @return
   */
  def string2Obj[T](str: String, clazz:Class[T]): Option[T] = {

    try{
      val obj = objectMapper.readValue(str, clazz)
      val result = Option.apply(obj)
      result
    } catch {
      case e: IOException =>
        logger.error("Parse String to Object error : {}" + e.getMessage)
        null
    }
  }


  def string2Obj[T](str: String, typeReference: TypeReference[T]): Option[T] = {
    if (StringUtils.isEmpty(str) || typeReference == null){
      return null
    }
    try {
      val value = objectMapper.readValue(str, typeReference)
      val openValue = Option.apply(value)
      openValue
    }
    catch {
      case e: IOException =>
        logger.error("Parse String to Object error", e)
        null
    }
  }
}
