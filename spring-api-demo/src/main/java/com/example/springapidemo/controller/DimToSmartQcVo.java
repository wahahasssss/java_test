package com.example.springapidemo.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/7/5
 * @Time 12:09 PM
 */
public class DimToSmartQcVo {

    private Integer id;
    private String dimDesc;
    private String dimNameEn;
    private String dimNameCn;
    private Integer tableId;
    private Integer tableType;
    private String codeValue;
    private String dimCondition;
    private Integer topicId;
    private List<Column> columns;

    public DimToSmartQcVo() {
    }

    public String getDimDesc() {
        return dimDesc;
    }

    public void setDimDesc(String dimDesc) {
        this.dimDesc = dimDesc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDimNameEn() {
        return dimNameEn;
    }

    public void setDimNameEn(String dimNameEn) {
        this.dimNameEn = dimNameEn;
    }

    public String getDimNameCn() {
        return dimNameCn;
    }

    public void setDimNameCn(String dimNameCn) {
        this.dimNameCn = dimNameCn;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTableType() {
        return tableType;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getDimCondition() {
        return dimCondition;
    }

    public void setDimCondition(String dimCondition) {
        this.dimCondition = dimCondition;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

}
