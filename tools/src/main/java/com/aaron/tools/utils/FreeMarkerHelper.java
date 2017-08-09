package com.aaron.tools.utils;


import freemarker.cache.MruCacheStorage;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;

import java.io.*;
import java.util.Map;

/**
 * Freemarker 工具处理类
 * <p>version:1.0.0</p>
 * <p>author: aaron.qiu</p>
 * <p>create: 2017-08-09</p>
 */
public class FreeMarkerHelper {

    private Configuration fileCfg = new Configuration(Configuration.VERSION_2_3_23);
    private Configuration strCfg = new Configuration(Configuration.VERSION_2_3_23);
    private StringTemplateLoader stringLoader = new StringTemplateLoader();

    public FreeMarkerHelper() {
        fileCfg.setClassForTemplateLoading(FreeMarkerHelper.class, "/");
        fileCfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        fileCfg.setCacheStorage(new MruCacheStorage(20,250));
        fileCfg.setDefaultEncoding("UTF-8");
        fileCfg.setNumberFormat("0.##;");
        fileCfg.setTemplateUpdateDelayMilliseconds(1000);
        fileCfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        strCfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        strCfg.setCacheStorage(new MruCacheStorage(20,250));
        strCfg.setDefaultEncoding("UTF-8");
        strCfg.setNumberFormat("0.##;");
        strCfg.setTemplateUpdateDelayMilliseconds(1000);
        strCfg.setTemplateLoader(stringLoader);
        strCfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    /**
     * 添加字符串模板
     * @param templateName 模板名称
     * @param templateStr 字符串模板
     */
    public FreeMarkerHelper putStringTemplate(String templateName,String templateStr){
        try {
            if(CommUtils.isEmpty(templateName)) throw new IllegalArgumentException("模板名称templateName不能为空");
            if(CommUtils.isEmpty(templateStr)) throw new IllegalArgumentException("模板内容templateStr不能为空");
            stringLoader.putTemplate(templateName,templateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 处理模板+数据，输出结果
     * @param templateName
     * @param dataMap 数据模型对象
     * @return 处理后的字符串
     * @throws IOException
     * @throws TemplateException
     */
    public String process(String templateName,Map dataMap) {
        if(CommUtils.isEmpty(templateName)) throw new IllegalArgumentException("模板名称templateName不能为空");
        StringWriter stringWriter = null;
        try {
            Template template = strCfg.getTemplate(templateName);
            stringWriter = new StringWriter();
            template.process(dataMap, stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null!=stringWriter) stringWriter.close();
            } catch (Exception e2) {
                //忽略
            }
        }

        return CommUtils.toStr(stringWriter);
    }

    public void processToFile(String templateName,Map root, String fileName) throws IOException, TemplateException{
        File resultFile = new File(fileName);
        if ( !resultFile.exists() ) {
            resultFile.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile),"UTF-8"));
        Template template=fileCfg.getTemplate(templateName);
        template.process(root, out);
    }



}
