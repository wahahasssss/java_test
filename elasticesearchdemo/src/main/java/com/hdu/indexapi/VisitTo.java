package com.hdu.indexapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author shushoufu
 * @date 2020/09/14
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VisitTo {
    private String id;
    private Long dt;
    private String ip;
    private String company_id;
    private String passport_id;
    private String device_id;
    private String phone;
    private String address;
    private String openId;
    private String visit_id;
    private String region;
    private String city;
    private String main_city_name;
    private String visit_time;
    private String visit_name;
    private String company_phone;
    private String bd_id;

    private String bus_time;
}
