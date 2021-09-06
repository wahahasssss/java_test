package com.hdu.requestlog.model

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

/**
 * @author shushoufu
 * @date 2020/08/26
 **/
case class Body(var identification:String,
                @JsonProperty("label_name") var labelName:String,
                @JsonProperty("is_hit") var isHit:String,
                var result:String,
                @JsonProperty("disposal_first") var disposalFirst:String,
                @JsonProperty("disposal_second") var disposalSecond:String,
                var message:String)

case class EntityMapTo(var ip:String,
                       @JsonProperty("company_id") var companyId:String,
                       @JsonProperty("device_id") var deviceId:String,
                       @JsonProperty ("passport_id") var passportId:String,
                       var phone:String,var address: String,
                       @JsonProperty ("open_id") var openId:String)

case class RequestLogParamsTo(@BeanProperty @JsonProperty("scene_key")  var sceneKey: String,
                              @JsonProperty ("entity_type") var entityType: String,
                              @JsonProperty ("entity_value") var entityValue: String,
                              @JsonProperty ("app_key") var appKey: String,
                              @JsonProperty ("event_id") var eventId: String,
                              @JsonProperty ("terminal_id") var terminalId: String,
                              @BeanProperty @JsonProperty ("entity_map") var entityMap: EntityMapTo,
                              @JsonProperty ("device_ext") var deviceExt: String,
                              @JsonProperty ("busi_ext") var businessExt: String,
                              @BeanProperty @JsonProperty ("request_time") var requestTime: Long)

case class RequestLogResultTo( var success:String,
                               @JsonProperty("error_code") var errorCode:String,
                               var message:String,
                               @BeanProperty var body:List[Body])

case class RcsRequestLogTo(@JsonProperty("request_time") var requestTime:String,
                           @JsonProperty("request_id") var requestId:String,
                           var method:String,
                           @BeanProperty var params:RequestLogParamsTo,
                           @JsonProperty("access_key") var accessKey:String,
                           @BeanProperty var times:Long,
                           @BeanProperty var results:RequestLogResultTo) {
  def this() {
    this(null,null,null,null,null,0L,null)
  }
}
