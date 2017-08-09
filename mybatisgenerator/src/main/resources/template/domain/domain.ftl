package com.allchips.${model}.common.bean;

import java.io.Serializable;

import java.util.Date;

/**
 * <p> ${author} </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： ${createTime} </p>
 * <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
 */
public class ${objectName} implements Serializable{
    <#if columnFieldList??>
    <#list columnFieldList as item>
    
    /**
     * <#if item.comment??>${item.comment}</#if>
     */
    private <#if item.javaType??>${item.javaType}</#if> <#if item.field??>${item.field}</#if>;
    </#list>
    
    <#list columnFieldList as item>
    public <#if item.javaType??>${item.javaType}</#if> get<#if item.cField??>${item.cField}</#if>()
    {
        return <#if item.field??>${item.field}</#if>;
    }
        
    public void set<#if item.cField??>${item.cField}</#if>(<#if item.javaType??>${item.javaType}</#if> <#if item.field??>${item.field}</#if>)
    {
        this.${item.field} = <#if item.field??>${item.field}</#if>;
    }
        
    </#list>
    </#if>

}
