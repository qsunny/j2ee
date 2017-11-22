//package com.aaron.springbootApp.web.controller;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.integration.file.remote.session.Session;
//import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
//import org.springframework.integration.support.MessageBuilder;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicReference;
//
//import static org.junit.Assert.assertTrue;
//
///**
// * <p>文件上传类</p>
// * <p>Author: Aaron.Qiu</p>
// * <p>Version: 1.0.0</p>
// * <p>Create Date：2017-08-31</p>
// * <p>Modified By：Aaron.Qiu</p>
// * <p>Modified Date: 2017-08-31</p>
// * <p>Copyright (c) 2017 ~ 2018 Allchips版权所有</p>
// */
//@Controller
//@RequestMapping(value="/api")
//public class FileUploadController {
//
//    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
//    public static final String UPLOAD_TEST_PREFIX_PATH = "/test";
//    public static final String UPLOAD_PROD_PREFIX_PATH = "/produce";
//    @Value("${spring.profiles.active}")
//    private String env;
//    @Resource
//    private FtpTools ftpTools;
//    @Autowired
//    private MessageChannel ftpChannel;
//    @Autowired
//    private AtomicReference<String> atomicReference;
//    @Autowired
//    private DefaultFtpSessionFactory ftpClientFactory;
//
//    @RequestMapping("/index")
//    public String welcome(Map<String, Object> model) throws Exception {
//        logger.info("==========welcome============");
//
//        return "hello";
//    }
//
//    /**
//     * 上传文件
//     * @param uploadType 上传的类型 如产品goods 品牌brand
//     * @param file
//     * @return
//     */
//    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResp<JSONObject> uploadFileHandler(@RequestParam("uploadType") String uploadType,
//                                                     @RequestParam("file") MultipartFile file) {
//
//        GenericResp<JSONObject> returnVo = new GenericResp<JSONObject>();
//        returnVo.setCode(BaseResponse.SUCCESS_RESULT.getResponseCode());
//        returnVo.setMessage(BaseResponse.SUCCESS_RESULT.getResponseMsgCn());
//        JSONObject resultJson = new JSONObject();
//        String randomNum = CommUtils.getRandom(5);
//
//        try {
//            File baseFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "presend");
//            if(!baseFolder.exists()) {
//                baseFolder.mkdirs();
//            }
//            //MultipartFile file = new MockMultipartFile("test1.jpg",new FileInputStream("F:\\aaron\\pic\\test1.jpg"));
//
//            if(CommUtils.isEmpty(uploadType)) {
//                returnVo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
//                returnVo.setMessage("uploadType不能为空");
//                return returnVo;
//            }
//
//            if (!file.isEmpty()) {
//
//                //文件上传到服务器的路径
//                String uploadFilePath = "";
//                String envPrefixEnv = "";
//                if("test".equals(env)||"dev".equals(env)) {
//                    envPrefixEnv = UPLOAD_TEST_PREFIX_PATH;
//                } else {
//                    envPrefixEnv = UPLOAD_PROD_PREFIX_PATH;
//                }
//
//                uploadFilePath = ProductConstant.UPLOAD_BASE_DIR + "/" + uploadType + "/" + randomNum + "/";
//
//                String originalFilename = CommUtils.toStr(file.getOriginalFilename());
//                String tempName = "";
//                String[] tempStrArr = originalFilename.split("\\.");
//                if(!CommUtils.isNull(tempStrArr)&&tempStrArr.length==2) {
//                    tempName = UUIDGenerator.generate() + "." + tempStrArr[1];
//                }
//
//                String filename = tempName;
//                String filePath = envPrefixEnv+uploadFilePath;
//
//                resultJson.put("uploadType",uploadType);
//                resultJson.put("originalFilename",originalFilename);
//                resultJson.put("filename",tempName);
//                logger.info("上传"+uploadType+"的文件路径: " + filePath+filename);
//
//                //boolean uploadFlag = ftpTools.uploadFile(file.getInputStream(), filename, filePath);
//                //设置ftp服务器保存文件路径
//                atomicReference.set(filePath);
//                final File fileToSend = new File(baseFolder, tempName);
//                FileUtils.copyInputStreamToFile(file.getInputStream(), fileToSend);
//                assertTrue(fileToSend.exists());
//                final Message<File> message = MessageBuilder.withPayload(fileToSend).build();
//
//                boolean uploadFlag = true;
//                ftpChannel.send(message);
//                if(uploadFlag) {
//                    resultJson.put("filePath",uploadFilePath + filename);
//                    resultJson.put("absolute",filePath + filename);
//                } else {
//                    returnVo.setCode(BaseResponse.FAIL_RESULT.getResponseCode());
//                    returnVo.setMessage("上传文件失败");
//                }
//
//                returnVo.setData(resultJson);
//            } else {
//                returnVo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
//                returnVo.setMessage("上传失败，上传文件是空！");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("uploadFileHandler uploadTypeId=" + randomNum + ",uploadType="+uploadType +"failed", e);
//            returnVo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
//            returnVo.setMessage("上传文件出错，请重试!");
//        }
//
//        return returnVo;
//    }
//
//
//
//
//    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
//    @ResponseBody
//    public GenericResp<JSONArray> uploadFileMulti(@RequestParam("uploadType")String uploadType,
//                                                  @RequestParam("files") MultipartFile[] uploadfiles) {
//
//
//        logger.debug("Multiple file upload!");
//        File baseFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "presend");
//        if(!baseFolder.exists()) {
//            baseFolder.mkdirs();
//        }
//
//        GenericResp<JSONArray> returnVo = new GenericResp<JSONArray>();
//        returnVo.setCode(BaseResponse.SUCCESS_RESULT.getResponseCode());
//        returnVo.setMessage(BaseResponse.SUCCESS_RESULT.getResponseMsgCn());
//        JSONArray jsonArray = new JSONArray();
//        JSONObject resultJson = new JSONObject();
//        String randomNum = CommUtils.getRandom(5);
//
//        if(CommUtils.isEmpty(uploadType)) {
//            returnVo.setCode(BaseResponse.ERROR_RESULT.getResponseCode());
//            returnVo.setMessage("uploadType不能为空");
//            return returnVo;
//        }
//
//        if(CommUtils.isNull(uploadfiles)||uploadfiles.length<=0) {
//            returnVo.setCode(BaseResponse.FAIL_RESULT.getResponseCode());
//            returnVo.setMessage("上传文件不能为空");
//            return returnVo;
//        }
//
//        try {
//
//            //文件上传到服务器的路径
//            String uploadFilePath = "";
//            String envPrefixEnv = "";
//            if("test".equals(env)||"dev".equals(env)) {
//                envPrefixEnv = UPLOAD_TEST_PREFIX_PATH;
//            } else {
//                envPrefixEnv = UPLOAD_PROD_PREFIX_PATH;
//            }
//            int i = 0;
//            for (MultipartFile multipartFile:uploadfiles) {
//                if (!multipartFile.isEmpty()) {
//
//                    randomNum = CommUtils.getRandom(5);
//                    resultJson = new JSONObject();
//                    uploadFilePath = ProductConstant.UPLOAD_BASE_DIR + "/" + uploadType + "/" + randomNum + "/";
//
//                    String originalFilename = CommUtils.toStr(multipartFile.getOriginalFilename());
//                    String tempName = "";
//                    String[] tempStrArr = originalFilename.split("\\.");
//                    if(!CommUtils.isNull(tempStrArr)&&tempStrArr.length==2) {
//                        tempName = UUIDGenerator.generate() + "." + tempStrArr[1];
//                    }
//
//                    String filename = tempName;
//                    String filePath = envPrefixEnv+uploadFilePath;
//
//                    resultJson.put("uploadType",uploadType);
//                    resultJson.put("originalFilename",originalFilename);
//                    resultJson.put("filename",tempName);
//                    logger.info("上传"+uploadType+"的文件路径: " + filePath+filename);
//                    //设置ftp服务器保存文件路径
//                    atomicReference.set(filePath);
////                    atomicReference.set("/test/product");
//                    //boolean uploadFlag = ftpTools.uploadFile(file.getInputStream(), filename, filePath);
//                    //final InputStream inputStreamA = file.getInputStream();
//                    //final InputStream inputStreamB = FtpOutboundChannelAdapterSample.class.getResourceAsStream("/test-files/b.txt");
//                    final File fileToSend = new File(baseFolder, tempName);
//
//                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), fileToSend);
//
//                    assertTrue(fileToSend.exists());
//                    final Message<File> message = MessageBuilder.withPayload(fileToSend).build();
//
//                    boolean uploadFlag = true;
//                    //this.createDirectory(filePath);
//                    ftpChannel.send(message);
//
//                    if(uploadFlag) {
//                        resultJson.put("filePath",uploadFilePath + filename);
//                        resultJson.put("absolute",filePath + filename);
//                        resultJson.put("flag",0);
//                        resultJson.put("msg","上传成功");
//                    } else {
//                        resultJson.put("flag",1);
//                        resultJson.put("msg","上传失败");
//                    }
//
//                    jsonArray.add(resultJson);
//
//                    //TimeUnit.MICROSECONDS.sleep(500);
//                }
//            }
//
//            returnVo.setData(jsonArray);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("uploadFileMulti uploadTypeId=" + randomNum + ",uploadType="+uploadType +"failed", e);
//            returnVo.setCode(BaseResponse.EXCEPTION_RESULT.getResponseCode());
//            returnVo.setMessage("上传文件出错，请重试!");
//        }
//
//        return returnVo;
//
//    }
//
//    private boolean createDirectory(String directoryPath) {
//        boolean isExist = Boolean.FALSE;
//        String[] directories = StringUtils.tokenizeToStringArray(directoryPath, "/");
//        String directory = "/";
//        for (String directorySegment : directories) {
//            directory += directorySegment+"/";
//            try {
//                Session session = ftpClientFactory.getSession();
//
//                if(!CommUtils.isNull(session)) {
//                    isExist = session.exists(directory);
//                    if (!isExist) {
//                        isExist = session.mkdir(directory);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return isExist;
//    }
//
//
//}
