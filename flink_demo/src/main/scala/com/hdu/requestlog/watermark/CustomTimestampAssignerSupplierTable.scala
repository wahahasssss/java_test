package com.hdu.requestlog.watermark

import com.hdu.requestlog.job.StudentsActivity
import com.hdu.requestlog.model.StudentsActivityInfo
import org.apache.flink.api.common.eventtime.{TimestampAssigner, TimestampAssignerSupplier}

/**
 * @author shushoufu
 * @date 2020/11/04
 **/
class CustomTimestampAssignerSupplierTable extends TimestampAssignerSupplier[StudentsActivity] {
  override def createTimestampAssigner(context: TimestampAssignerSupplier.Context): TimestampAssigner[StudentsActivity] = {
    return new TimestampAssigner[StudentsActivity] {
      override def extractTimestamp(t: StudentsActivity, l: Long): Long = {
        val ts = t.activityTimestamp
        ts
      }
    }
  }
}
