package com.example.springapidemo.controller;

import lombok.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/7/5
 * @Time 1:30 PM
 */

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexToSmartQcVo {
    private Integer id;
    private String indexNameEn;
    private String indexNameCn;
    private String indexDesc;
    private Integer topicId;
    private Integer indexType;

}
