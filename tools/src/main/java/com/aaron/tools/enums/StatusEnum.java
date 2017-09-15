package com.aaron.tools.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>通用枚举状态</p>
 * <p> aaron.qiu </p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date： 2017-08-25 19:42:37 </p>
 * <p>Copyright (c) 2017 ~ 2018 版权所有</p>
 */
public enum StatusEnum {
    DISABLED_STATUS(0,"disabled","停用"),
    ENABLED_STATUS(1,"enabled","启用");
    //状态码
    private int statusCode;
    //状态英文
    private String statusMsgEn;
    //状态中文
    private String statusMsgCn;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<Integer, StatusEnum> lookup = new HashMap<Integer, StatusEnum>();

    static {
        for (StatusEnum ase : StatusEnum.values()) {
            lookup.put(ase.getStatusCode(), ase);
        }
    }

    private StatusEnum(int statusCode,String statusMsgEn,String statusMsgCn) {
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

    public static StatusEnum get(Integer statusCode) {
        return lookup.get(statusCode);
    }
}
