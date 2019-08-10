package com.aaron.springbootDemo.enums.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>数据通用状态:启用  停用</p>
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2019-08-10 19:42:37 </p>
 * <p>Copyright (c) 2019 ~ 2020 版权所有</p>
 */
public enum CommonStatusEnum {
    //启用状态 10启用  20弃用
    AVAILABLE(10,"available","启用"),
    UNAVAILABLE(20,"unavailable","停用");
    //状态码
    private int statusCode;
    //状态英文
    private String statusMsgEn;
    //状态中文
    private String statusMsgCn;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<Integer, CommonStatusEnum> lookup = new HashMap<>();

    static {
        for (CommonStatusEnum ase : CommonStatusEnum.values()) {
            lookup.put(ase.getStatusCode(), ase);
        }
    }

    private CommonStatusEnum(int statusCode, String statusMsgEn, String statusMsgCn) {
        this.statusCode = statusCode;
        this.statusMsgEn = statusMsgEn;
        this.statusMsgCn = statusMsgCn;
    }

    public int getStatusCode() {
        return statusCode;
    }


    public String getStatusMsgEn() {
        return statusMsgEn;
    }

    public String getStatusMsgCn() {
        return statusMsgCn;
    }

    public static CommonStatusEnum get(Integer statusCode) {
        return lookup.get(statusCode);
    }
}
