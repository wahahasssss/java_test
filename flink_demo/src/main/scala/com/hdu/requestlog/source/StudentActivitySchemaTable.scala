package com.hdu.requestlog.source

import java.nio.charset.StandardCharsets

import com.hdu.requestlog.job.StudentsActivity
import com.hdu.requestlog.model.StudentsActivityInfo
import com.hdu.requestlog.utils.JsonUtil
import org.apache.flink.api.common.serialization.AbstractDeserializationSchema

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
class StudentActivitySchemaTable extends AbstractDeserializationSchema[StudentsActivity] {

  override def deserialize(bytes: Array[Byte]): StudentsActivity = {
    val studentActivityString = new String(bytes, StandardCharsets.UTF_8)
    val studentActivity = JsonUtil.string2Obj(studentActivityString, classOf[StudentsActivity]).get
    studentActivity
  }
}
