package com.aaron.springbootDemo.web.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);


    @Value("${xxl.job.admin.addresses}")
    private String addresses;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logpath;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() throws Exception {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        try {
            XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
            xxlJobExecutor.setIp(ip);
            xxlJobExecutor.setPort(port);
            xxlJobExecutor.setAppName(appname);
            xxlJobExecutor.setAdminAddresses(addresses);
            xxlJobExecutor.setLogPath(logpath);
            xxlJobExecutor.setAccessToken(accessToken);
            return xxlJobExecutor;
        } catch(Exception e) {
            logger.error("执行器注册失败",e);
            e.printStackTrace();
            throw new Exception("执行器注册失败");
        }
    }

}