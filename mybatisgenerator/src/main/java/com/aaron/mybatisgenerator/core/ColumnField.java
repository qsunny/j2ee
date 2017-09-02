package com.aaron.mybatisgenerator.core;

public class ColumnField
{
    private String column;
    private String field;
    private String cField;
    private String jdbcType;
    private String javaType;
    private String comment;

    public String getColumn()
    {
        return this.column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getJdbcType() {
        return this.jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getcField() {
        return this.cField;
    }

    public void setcField(String cField) {
        this.cField = cField;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}