package com.aaron.springbootDemo.common.exception;

import com.allchips.tools.enums.BaseResponse;

/**
 * SpringbootDemoException
 * @author Aaron.Qiu
 * @since 2019-01-16
 * @version 1.0.0
 * Copyright (c) 2019 ~ 2010 版权所有
 */
public class SpringbootDemoException extends Exception{

    private int code = BaseResponse.ERROR_RESULT.getResponseCode();

    public SpringbootDemoException() {
        super();
    }

    public SpringbootDemoException(int code) {
        super();
        this.code = code;
    }

    public SpringbootDemoException(String message, int code) {
        super(message);
        this.code = code;
    }

    public SpringbootDemoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public SpringbootDemoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public SpringbootDemoException(String arg0) {
        super(arg0);
    }

    public SpringbootDemoException(Throwable arg0) {
        super(arg0);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
