package com.tulang.beans.${model}.${fLowerObjectName};

import java.io.Serializable;

import java.util.Date;

/**
 * <p> ${author} </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： ${createTime} </p>
 * <p>Copyright (c) 2017 ~ 2022 Tulang版权所有</p>
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

    @Override
    public String toString() {
    return "${objectName}{" +
    <#list columnFieldList as item>
    <#if item_index==0>
        "<#if item.field??>${item.field}</#if>=" + <#if item.field??>${item.field}</#if> +
    <#else>
    ", <#if item.field??>${item.field}</#if>=" + <#if item.field??>${item.field}</#if> +
    </#if>
    </#list>
    '}';
    }

    </#if>

}
