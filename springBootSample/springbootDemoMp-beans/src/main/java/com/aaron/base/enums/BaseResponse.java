package com.aaron.base.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应基础信息
 * @author aaron.qiu
 * @since 2021-2025
 */
public enum BaseResponse {
    SUCCESS_RESULT(0,"成功","SUCCESS"),
    FAIL_RESULT(400,"失败","FAIL"),
    EXCEPTION_RESULT(500,"异常","EXCEPTION"),
    ERROR_RESULT(501,"错误","ERROR"),
    NOLOGIN_RESULT(510,"用户没有登录","NO LOGIN"),
    NOACCESS_RESULT(511,"没有权限","NO ACCESS"),
    DATABASE_ERROR(101,"系统异常，请联系管理员！",""),
    /**
     * access_token过期
     */
    EXPIRED_ACCESS_TOKEN(102,"请登录", "")
    ;

    //响应状态码
    private int responseCode;
    //响应英文信息
    private String responseMsgEn;
    //响应中文信息
    private String responseMsgCn;

    // Reverse-lookup map for getting a day from an abbreviation
    private static final Map<Integer, BaseResponse> lookup = new HashMap<Integer, BaseResponse>();

    static {
        for (BaseResponse br : BaseResponse.values()) {
            lookup.put(br.getResponseCode(), br);
        }
    }

    private BaseResponse(int responseCode, String responseMsgCn, String responseMsgEn) {
        this.responseCode = responseCode;
        this.responseMsgCn = responseMsgCn;
        this.responseMsgEn = responseMsgEn;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsgEn() {
        return responseMsgEn;
    }

    public String getResponseMsgCn() {
        return responseMsgCn;
    }

    public static BaseResponse get(int responseCode) {
        return lookup.get(responseCode);
    }

    public String toString(){  //覆盖了父类Enum的toString()
        return super.toString()+"["+responseCode+","+responseMsgEn+","+responseMsgCn+"]";
    }

}
