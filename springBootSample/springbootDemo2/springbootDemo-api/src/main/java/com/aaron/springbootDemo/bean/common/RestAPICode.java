package com.aaron.springbootDemo.bean.common;

/**
 * RESTful API响应码
 *
 */
public enum RestAPICode {
    /***************************BASE ERROR CODE 系统级响应码 start  0-999******************************/
    
	/**
     * 正常响应
     */
    NORMAL("success", 0),
    DATABASE_ERROR("系统异常，请联系管理员！", 101),
    /** access_token相关错误 2xxx */
    /**
     * access_token过期
     */
    EXPIRED_ACCESS_TOKEN("请登录", 102),
    /** 请求参数相关错误 */
    /**
     * HTTP请求头参数错误
     */
    REQUEST_HEADER_PARAM_ERROR("http request header param error", 103),
    /**
     * JSON请求参数字段错误
     */
    REQUEST_JSON_FIELD_ERROR("http request json param error", 104),
    /**
     * HTTP请求参数字段错误，适用于GET/POST方式传递的字段参数
     */
    REQUEST_PARAM_FIELD_ERROR("http request param error", 105),
    SYS_SIGN_INVALID("签名无效", 106);

    private String message;
    private int code;

    private RestAPICode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
