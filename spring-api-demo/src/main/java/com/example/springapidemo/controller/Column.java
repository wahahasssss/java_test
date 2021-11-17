package com.example.springapidemo.controller;

public class Column {
    private Integer id;
    private Integer dimId;
    private Integer columnId;
    private Integer isPk;
    private Integer tableId;

    public Column() {
    }

    public Column(Integer id, Integer dimId, Integer columnId, Integer isPk, Integer tableId) {
        this.id = id;
        this.dimId = dimId;
        this.columnId = columnId;
        this.isPk = isPk;
        this.tableId = tableId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDimId() {
        return dimId;
    }

    public void setDimId(Integer dimId) {
        this.dimId = dimId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getIsPk() {
        return isPk;
    }

    public void setIsPk(Integer isPk) {
        this.isPk = isPk;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "Column{" +
                "id=" + id +
                ", dimId=" + dimId +
                ", columnId=" + columnId +
                ", isPk=" + isPk +
                ", tableId=" + tableId +
                '}';
    }
}