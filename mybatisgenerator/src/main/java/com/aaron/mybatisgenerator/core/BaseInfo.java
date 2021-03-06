package com.aaron.mybatisgenerator.core;

import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class BaseInfo
{
    private String createTime;
    private String author;
    private String model;
    private List<ColumnField> columnFieldList;
    private String subModel;
    private String tableName;
    private String objectName;
    private String fLowerObjectName;

    public String getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSubModel() {
        return StringUtils.uncapitalize(this.objectName);
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getfLowerObjectName() {
        return this.fLowerObjectName;
    }

    public void setfLowerObjectName(String fLowerObjectName) {
        if ((fLowerObjectName == null) || (fLowerObjectName == "") ||
                ((fLowerObjectName + "*").equals("*")))
            this.fLowerObjectName = StringUtils.uncapitalize(this.objectName);
        else
            this.fLowerObjectName = fLowerObjectName;
    }

    public List<ColumnField> getColumnFieldList()
    {
        return this.columnFieldList;
    }

    public void setColumnFieldList(List<ColumnField> columnFieldList) {
        this.columnFieldList = columnFieldList;
    }
}
