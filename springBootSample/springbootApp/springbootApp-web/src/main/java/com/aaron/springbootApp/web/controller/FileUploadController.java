package com.aaron.springbootApp.web.controller;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>文件上传类</p>
 * <p>Author: Aaron.Qiu</p>
 * <p>Version: 1.0.0</p>
 * <p>Create Date：2017-08-31</p>
 * <p>Modified By：Aaron.Qiu</p>
 * <p>Modified Date: 2017-08-31</p>
 * <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
 */
@Controller
@RequestMapping(value="/api")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
    public static final String UPLOAD_TEST_PREFIX_PATH = "/test";
    public static final String UPLOAD_PROD_PREFIX_PATH = "/produce";
    @Value("${spring.profiles.active}")
    private String env;
    @Resource
    private FtpTools ftpTools;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @RequestMapping("/index")
    public String welcome(Map<String, Object> model) throws Exception {
        System.out.println("==========welcome============");

        return "hello";
    }

    /**
     * 上传文件
     * @param uploadType 上传的类型 如产品goods 品牌brand
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public GenericResp<JSONObject> uploadFileHandler(@RequestParam("uploadType") String uploadType,
                                                     @RequestParam("file") MultipartFile file) {

        GenericResp<JSONObject> returnVo = new GenericResp<JSONObject>();
        returnVo.setCode(BaseResponse.SUCCESS_RESULT.getResponseCode());
        returnVo.setMessage(BaseResponse.SUCCESS_RESULT.getResponseMsgCn());
        JSONObject resultJson = new JSONObject();
        String randomNum = CommUtils.getRandom(5);
        try {
            //MultipartFile file = new MockMultipartFile("test1.jpg",new FileInputStream("F:\\aaron\\pic\\test1.jpg"));

            if(CommUtils.isEmpty(uploadType)) {
                returnVo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
                returnVo.setMessage("uploadType不能为空");
                return returnVo;
            }

            if (!file.isEmpty()) {

                //文件上传到服务器的路径
                String uploadFilePath = "";
                String envPrefixEnv = "";
                if("test".equals(env)||"dev".equals(env)) {
                    envPrefixEnv = UPLOAD_TEST_PREFIX_PATH;
                } else {
                    envPrefixEnv = UPLOAD_PROD_PREFIX_PATH;
                }

                uploadFilePath = ProductConstant.UPLOAD_BASE_DIR + "/" + uploadType + "/" + randomNum + "/";

                String tempName = file.getOriginalFilename();
                String filename = System.currentTimeMillis() +"_"+ tempName;
    //            if(!CommUtils.isEmpty(tempName)&&tempName.lastIndexOf(".")>=0) {
    //                filename = filename + tempName.substring(tempName.lastIndexOf("."));
    //            }
                String filePath = envPrefixEnv+uploadFilePath;

                resultJson.put("uploadType",uploadType);
                resultJson.put("originalFilename",tempName);
                resultJson.put("filename",filename);
                log.info("上传"+uploadType+"的文件路径: " + filePath+filename);

                boolean uploadFlag = ftpTools.uploadFile(file.getInputStream(), filename, filePath);
                if(uploadFlag) {
                    resultJson.put("filePath",uploadFilePath + filename);
                    resultJson.put("absolute",filePath + filename);
                } else {
                    returnVo.setCode(BaseResponse.FAIL_RESULT.getResponseCode());
                    returnVo.setMessage("上传文件失败");
                }

                returnVo.setData(resultJson);
            } else {
                returnVo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
                returnVo.setMessage("上传失败，上传文件是空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("uploadFileHandler uploadTypeId=" + randomNum + ",uploadType="+uploadType +"failed", e);
            returnVo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
            returnVo.setMessage("上传文件出错，请重试!");
        }

        return returnVo;
    }




}
