package com.aaron.springbootDemo.core.pager;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;


/**
 * Mybatis dao效率监控，监控所有的update、query语句
 *
 * @author paul
 * @since 2015-10-28
 */
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
	public class DaoTimeConsumingMonitor implements Interceptor {
	private Logger log = Logger.getLogger(getClass());
	private static final String logScope = "Dao";

	@Override
	public Object intercept(Invocation invocation) throws Exception {
	    MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
	    Object parameter = null;
	    if (invocation.getArgs().length > 1) {
	        parameter = invocation.getArgs()[1];
	    }
	    String sqlId = mappedStatement.getId();
	    BoundSql boundSql = mappedStatement.getBoundSql(parameter);
	    Configuration configuration = mappedStatement.getConfiguration();
	    Object rtn = null;
	    long start = System.currentTimeMillis();
	    rtn = invocation.proceed();
	    long end = System.currentTimeMillis();
	    long time = (end - start);
	
	
	    String sql = showSql(configuration, boundSql);
	    // String log = getSql(configuration, boundSql, sqlId, time);
	    // System.err.println(log);
//	    log.info(HttpUtil.getLocalIpAddress()+ "|" + logScope+ "|" + sqlId+ "|" + sql+ "|" +time);
	    //logger.business(HttpUtil.getLocalIpAddress(), logScope, sqlId, sql, time);
	
	    return rtn;
	}
	
	@SuppressWarnings("unused")
	private static String getSql(Configuration configuration, BoundSql boundSql, String sqlId, long time) {
	    String sql = showSql(configuration, boundSql);
	    StringBuilder str = new StringBuilder(100);
	    str.append(sqlId);
	    str.append(":");
	    str.append(sql);
	    str.append(":");
	    str.append(time);
	    str.append("ms");
	    return str.toString();
	}
	
	private static String showSql(Configuration configuration, BoundSql boundSql) {
	    Object parameterObject = boundSql.getParameterObject();
	    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
	    String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
	    if (parameterMappings.size() > 0 && parameterObject != null) {
	        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
	        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
	            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
	
	        } else {
	            MetaObject metaObject = configuration.newMetaObject(parameterObject);
	            for (ParameterMapping parameterMapping : parameterMappings) {
	                String propertyName = parameterMapping.getProperty();
	                if (metaObject.hasGetter(propertyName)) {
	                    Object obj = metaObject.getValue(propertyName);
	                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
	                } else if (boundSql.hasAdditionalParameter(propertyName)) {
	                    Object obj = boundSql.getAdditionalParameter(propertyName);
	                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
	                }
	            }
	        }
	    }
	    return sql;
	}
	
	private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        //转义'$','/'特殊字符
        value = Matcher.quoteReplacement(value);
        return value;
    }
	
	@Override
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
	
	}

}

