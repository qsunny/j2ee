package com.aaron.springweb.bean;

/**
 * Created by aaron.qiu on 2016/9/25.
 */
public class SpringwebConfigBean {

    private String appName;
    private String jdbcName;

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public String getJdbcName() {
        return jdbcName;
    }

    public void setJdbcName(String jdbcName) {
        this.jdbcName = jdbcName;
    }
}
