package com.example.springapidemo.controller;

import lombok.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/7/5
 * @Time 10:20 AM
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetadataVo {
    private Integer appId;
    private Integer domainId;
    private Integer dataType;
    private String metaOperation;
    private Object customVariables;
    private Integer templateId;
    private String submitter;
    private String auditor;
}
