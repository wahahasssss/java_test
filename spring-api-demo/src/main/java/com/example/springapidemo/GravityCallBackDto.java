package com.example.springapidemo;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GravityCallBackDto {
    private Integer appId;
    private Integer domainId;
    //jsonString格式
    private String customVariables;
    private Integer metaObject;
    private Integer metaOperation;
    private String processKey;
}