package com.aaron.springweb.web.controller;

import com.aaron.springweb.web.config.SpringwebRootConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron.qiu on 2016/9/25.
 */
@Controller
@RequestMapping("/springweb")
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Value("#{webAppConfig.appName}")
    private String applicationName;
    @Value("#{webAppConfig.jdbcName}")
    private String jdbcName;


    @Autowired
    private SpringwebRootConfig springwebRootConfig;


    @RequestMapping("/index")
    public String index(Map<String, Object> model) {
        log.debug("index() is executed!");
        //SpringwebConfigBean configBean = springwebRootConfig.webAppConfig();
        //applicationName =configBean.getAppName();
        log.info(applicationName);
        model.put("title", applicationName);
        model.put("jdbcName", jdbcName);
        model.put("msg", "Hello Gradle!");

        return "index";
    }

    /**
     * 响应回前台自动转换成json格式
     * springmvc-dispatcher-servlet.xml ==>配置 RequestMappingHandlerAdapter
     * <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
     * 添加依赖包
     * compile 'com.fasterxml.jackson.core:jackson-core:2.7.4'
     * compile 'com.fasterxml.jackson.core:jackson-databind:2.7.4'
     * compile 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.4'
     * compile 'com.fasterxml.jackson.module:jackson-module-parameter-names:2.7.4'
     * @param model
     * @return {"country": "CN","name": "aaron","age": "30"	}
     */
    @ResponseBody
    @RequestMapping(path="/jsonConvert", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String,String> jsonConvert(Map<String, Object> model) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("name","aaron");
        map.put("age","30");
        map.put("country","CN");
        return map;
    }
}
