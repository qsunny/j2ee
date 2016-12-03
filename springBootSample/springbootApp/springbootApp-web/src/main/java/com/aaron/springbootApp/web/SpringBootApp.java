package com.aaron.springbootApp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by aaron.qiu on 2016/11/24.
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
 * @SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
 */
@SpringBootApplication(scanBasePackages = {"com.aaron.springbootApp.core.*,com.aaron.springbootApp.web"})
public class SpringBootApp {

    //private static Log logger = LogFactory.getLog(SpringBootApp.class);

    public static void main(String[] args) throws Exception {
        //禁止重启
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringBootApp.class, args);
    }
}
