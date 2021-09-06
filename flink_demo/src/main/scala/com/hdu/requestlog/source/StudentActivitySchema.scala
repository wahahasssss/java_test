package com.hdu.requestlog.source

import java.nio.charset.{Charset, StandardCharsets}

import com.hdu.requestlog.model.StudentsActivityInfo
import com.hdu.requestlog.utils.JsonUtil
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
class StudentActivitySchema extends AbstractDeserializationSchema[StudentsActivityInfo] {

  override def deserialize(bytes: Array[Byte]): StudentsActivityInfo = {
    val studentActivityString = new String(bytes, StandardCharsets.UTF_8)
    val studentActivity = JsonUtil.string2Obj(studentActivityString, classOf[StudentsActivityInfo]).get
    studentActivity
  }
}
