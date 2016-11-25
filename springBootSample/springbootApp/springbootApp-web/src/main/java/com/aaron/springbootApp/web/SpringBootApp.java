package com.aaron.springbootApp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aaron.qiu on 2016/11/24.
 * @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
 * @SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
 */
@Configuration
@ComponentScan(basePackages="com.aaron.springbootApp")
@EnableAutoConfiguration
public class SpringBootApp {

    public static void main(String[] args) throws Exception {
        //禁止重启
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SpringBootApp.class, args);
    }
}
