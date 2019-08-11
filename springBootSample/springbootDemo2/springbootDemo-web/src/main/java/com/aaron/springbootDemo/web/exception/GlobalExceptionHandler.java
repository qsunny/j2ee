package com.aaron.springbootDemo.web.exception;

import com.aaron.springbootDemo.bean.common.GenericResp;
import com.aaron.springbootDemo.enums.common.BaseResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aaron.Qiu on 2017-07-25.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }*/

    @ExceptionHandler(value = Throwable.class)
    public GenericResp<String> jsonErrorHandler(HttpServletRequest req, Throwable e){
        GenericResp<String> errorInfo = new GenericResp<>();
//        log.info("============="+e.getClass().getName());
//        errorInfo.setMessage(e.getMessage());
        errorInfo.setMessage("参数解析异常");
        errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
        errorInfo.setData(req.getRequestURL().toString());
        log.error("参数解析异常",e);
        return errorInfo;
    }

    @ExceptionHandler(value = JsonMappingException.class)
    public GenericResp<String> parseJsonErrorHandler(HttpServletRequest req, JsonMappingException e) {
        GenericResp<String> errorInfo = new GenericResp<>();
        log.info("============="+e.getClass().getName());
        errorInfo.setMessage("参数解析异常");
        errorInfo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
        errorInfo.setData(req.getRequestURL().toString());
        return errorInfo;
    }


}
