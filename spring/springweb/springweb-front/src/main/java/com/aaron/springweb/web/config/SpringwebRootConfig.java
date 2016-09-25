package com.aaron.springweb.web.config;

import com.aaron.springweb.bean.SpringwebConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * Created by aaron.qiu on 2016/9/25.
 */
@Configuration
@Import({RedisConfig.class})
@ComponentScan(basePackages = { "com.aaron.springweb.core" },
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {
        "com.aaron.springweb.web.*" }))
//@PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
//@Profile("prod") //两种方式
public class SpringwebRootConfig {

    @Autowired
    private Environment env;

    @Configuration
    @PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
    @Profile("dev")
    public static class devConfig {

    }

    @Configuration
    @PropertySource(value="classpath:application-${spring.profiles.active}.properties",ignoreResourceNotFound=true)
    @Profile("prod")
    public static class prodConfig {

    }

    @Bean
    public SpringwebConfigBean webAppConfig() {
        SpringwebConfigBean webAppConfig = new SpringwebConfigBean();
        webAppConfig.setAppName(env.getProperty("app.name"));
        webAppConfig.setJdbcName(env.getProperty("db.name"));
        return webAppConfig;
    }

}
