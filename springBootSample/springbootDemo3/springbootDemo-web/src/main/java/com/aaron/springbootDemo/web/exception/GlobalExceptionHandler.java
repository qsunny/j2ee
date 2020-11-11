package com.aaron.springbootDemo.web.exception;


import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by Aaron.Qiu on 2020-10-30.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /*private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {org.springframework.http.converter.HttpMessageNotReadableException.class, org.springframework.http.converter.HttpMessageConversionException.class, com.fasterxml.jackson.databind.exc.MismatchedInputException.class, com.fasterxml.jackson.core.JsonProcessingException.class})
    public GenericResp<String> dataBindExceptionHandler(HttpServletRequest req, Throwable e) {
        GenericResp<String> errorInfo = new GenericResp<>();
        errorInfo.setMessage("参数解析异常");
        errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
        log.error("dataBindExceptionHandler", e);
        return errorInfo;
    }

    @ExceptionHandler(value = Throwable.class)
    public GenericResp<String> jsonErrorHandler(HttpServletRequest req, Throwable e){
        GenericResp<String> errorInfo = new GenericResp<>();
        if(CommUtils.isNull(e)) {
            errorInfo.setMessage("json参数解析异常,"+ CommUtils.toStr(req.getRequestURI()));
            errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
            log.error(e.getMessage());
            return errorInfo;
        }
        log.error("jsonErrorHandler异常",e);
//        log.info("============="+e.getClass().getName());
//        errorInfo.setMessage(e.getMessage());

        if(e instanceof UndeclaredThrowableException) {
            BomException be = (BomException)((UndeclaredThrowableException)e).getUndeclaredThrowable();
            errorInfo.setMessage(be.getMessage());
            errorInfo.setCode(be.getCode());
        } else if (CommUtils.isNotNull(e.getCause())
                && CommUtils.isNotEmpty(e.getCause().getMessage())) {
            if(e.getCause()instanceof BomException) {
                errorInfo.setMessage(e.getCause().getMessage());
                errorInfo.setCode(((BomException) e.getCause()).getCode());
            } else {
                String message = e.getCause().getMessage();
                log.error("接口请求异常:" + CommUtils.toStr(req.getRequestURI()) + message, e);
                //TODO 返回文本信息引发歧义  将参数解析异常 修改为 请求异常
                errorInfo.setMessage("请求异常");
                errorInfo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
            }
        } else {
            log.error(e.getMessage()+"---"+req.getRequestURL().toString(),e);
            errorInfo.setMessage("参数解析异常");
            errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
        }

        errorInfo.setData(req.getRequestURL().toString());
        log.error("接口请求异常:"+ CommUtils.toStr(req.getRequestURI()),e);
        return errorInfo;
    }

    @ExceptionHandler(value = JsonMappingException.class)
    public GenericResp<String> parseJsonErrorHandler(HttpServletRequest req, JsonMappingException e) {
        log.error("jsonErrorHandler异常",e);
        GenericResp<String> errorInfo = new GenericResp<>();
        log.info("============="+e.getClass().getName());
        errorInfo.setMessage("参数解析异常");
        errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
        errorInfo.setData(req.getRequestURL().toString());
        return errorInfo;
    }*/
}
