package com.aaron.springbootApp.web.exception;

import com.aaron.springbootApp.exception.ErrorInfo;
import com.aaron.springbootApp.exception.SpringbootAppException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aaron.Qiu on 2016/12/3.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = MultipartException.class)
    @ResponseBody
    public ErrorInfo<String> handleMultipartExceptionHandler(HttpServletRequest req, MultipartException ex) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        String maxFileSize = getMaxUploadFileSize(ex);
        if (maxFileSize != null) {
            errorInfo.setMessage("Uploaded file is too large.  File size cannot exceed " + maxFileSize + ".");
        }

        errorInfo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
        errorInfo.setData(req.getRequestURL().toString());
        return errorInfo;
    }

    private String getMaxUploadFileSize(MultipartException ex) {
        if (ex instanceof MaxUploadSizeExceededException) {
            return asReadableFileSize(((MaxUploadSizeExceededException)ex).getMaxUploadSize());
        }
        String msg = ex.getMessage();
        if (msg.contains("SizeLimitExceededException")) {
            String maxFileSize = msg.substring(msg.indexOf("maximum")).replaceAll("\\D+", "");
            if (StringUtils.isNumeric(maxFileSize)) {
                return asReadableFileSize(Long.valueOf(maxFileSize));
            }
        }

        return null;
    }

    // http://stackoverflow.com/a/5599842/225217
    private static String asReadableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = SpringbootAppException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, SpringbootAppException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setData("Some Data");
        errorInfo.setUrl(req.getRequestURL().toString());
        return errorInfo;
    }
}
