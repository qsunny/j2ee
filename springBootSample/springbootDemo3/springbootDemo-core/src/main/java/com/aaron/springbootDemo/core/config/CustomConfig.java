package com.aaron.springbootDemo.core.config;

import com.allchips.common.util.FtpTools;
import com.allchips.tools.utils.CommUtils;
import com.allchips.tools.utils.FreeMarkerHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义相关实体类配置注入
 */
@Configuration
//@ImportResource("classpath*:spring_pool.xml")
public class CustomConfig {

    private static final Logger log = LoggerFactory.getLogger(CustomConfig.class);

    /**
     * 处理freemarker模板工具类
     * @return
     */
    @Bean(name = {"freeMarkerHelper"})
    public FreeMarkerHelper freeMarkerHelper() {
        return new FreeMarkerHelper();
    }

}
