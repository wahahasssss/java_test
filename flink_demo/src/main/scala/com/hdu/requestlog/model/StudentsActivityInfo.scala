package com.hdu.requestlog.model

/**
 * @author shushoufu
 * @date 2020/11/03
 **/
case class StudentsActivityInfo(var name: String,
                                var age: Int,
                                var birthday: String,
                                var activityTime: String,
                                var activity: String,
                                var activityTimestamp: Long) extends Serializable{
  def this() {
    this("",0,"","","",0L)
  }
}
