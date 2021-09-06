package com.hdu.requestlog.watermark

import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.api.common.eventtime.{TimestampAssigner, TimestampAssignerSupplier}

/**
 * @author shushoufu
 * @date 2020/11/04
 **/
class CustomTimestampAssignerSupplier extends TimestampAssignerSupplier[StudentsActivityInfo]{
  override def createTimestampAssigner(context: TimestampAssignerSupplier.Context): TimestampAssigner[StudentsActivityInfo] = {
    return new TimestampAssigner[StudentsActivityInfo] {
      override def extractTimestamp(t: StudentsActivityInfo, l: Long): Long = {
        val ts = t.activityTimestamp
        ts
      }
    }
  }
}
