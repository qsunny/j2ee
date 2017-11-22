package com.allchips.product.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ftpClient相关配置
 */
@Configuration
@ImportResource("classpath*:ftp_outbound_channel_adapter_context.xml")
public class FtpConfig {

    private static final Logger log = LoggerFactory.getLogger(FtpConfig.class);


}
