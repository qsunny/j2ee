<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.allchips.${model}.core.dao.${fLowerObjectName}.I${objectName}Dao" >
    <resultMap id="${objectName}Map" type="${objectName}" >
    <#if columnFieldList??>
    <#list columnFieldList as item>
        <#if item.column=='ID'><id column="${item.column}" property="${item.field}" jdbcType="${item.jdbcType}" /></#if>
        <#if item.column!='ID'><result column="${item.column}" property="${item.field}" jdbcType="${item.jdbcType}" /></#if>
    </#list>
    </#if>
    </resultMap>
    
    <sql id="Base_Column_List" >
        <#if columnFieldList??>
        <#list columnFieldList as item>
        ${item.column}<#if item_has_next>,</#if>
        </#list>
        </#if>
    </sql>

    <sql id="normalWhere">
    <#if columnFieldList??>
        <#list columnFieldList as item>
        <if test="${item.field}!=null">
            and ${item.column} = ${"#{"}${item.field},jdbcType=${item.jdbcType}${"}"}
        </if>
		</#list>
    	</#if>
    </sql>

    <sql id="where">
        <#if columnFieldList??>
        <#list columnFieldList as item>
        <if test="${objectName?lower_case}.${item.field}!=null">
        and ${item.column} = ${"#{"}${objectName?lower_case}.${item.field},jdbcType=${item.jdbcType}${"}"}
        </if>
		</#list>
    	</#if>
    </sql>
    
    <select id="get${objectName}ById" resultMap="${objectName}Map" parameterType="java.lang.String" >
        select 
        <include refid="Base_Column_List" />
        from ${tableName}
        where ID = ${"#{"}id,jdbcType=VARCHAR${"}"}
    </select>
    
    <select id="getAll" parameterType="${objectName}" resultMap="${objectName}Map">
        select * from ${tableName} where 1=1 
        <include refid="normalWhere" />
    </select>
    
    <select id="getPagerModelByQuery" parameterType="${objectName}" resultMap="${objectName}Map">
        select * from ${tableName} where 1=1 
        <include refid="where" />
    </select>
    
    <select id="getByPageCount" parameterType="${objectName}" resultType="int">
        select count(1) from ${tableName} where 1=1 
        <include refid="normalWhere" />
    </select>
   
    <insert id="insert${objectName}" parameterType="${objectName}" >
        insert into ${tableName} (<include refid="Base_Column_List" />)
        values (
        <#if columnFieldList??>
        <#list columnFieldList as item>
        ${"#{"}${item.field},jdbcType=${item.jdbcType}${"}"}<#if item_has_next>,</#if>
        </#list>
        </#if>)
    </insert>
    
    <delete id="del${objectName}ById" parameterType="java.lang.String" >
        delete from ${tableName}
        where ID = ${"#{"}id,jdbcType=VARCHAR${"}"}
    </delete>
    
    <update id="update${objectName}" parameterType="${objectName}" >
        update ${tableName}
        <set>
      <#if columnFieldList??>
      <#list columnFieldList as item>
            <#if item.field != "id" && item.field != "userId">
                <if test="${item.field} != null " >
                    ${item.column} = ${"#{"}${item.field},jdbcType=${item.jdbcType}${"}"}<#if item_has_next>,</#if>
                </if>
            </#if>
      </#list>
      </#if>
        </set>
        where ID = ${"#{"}id,jdbcType=BIGINT${"}"}
    </update>
</mapper>