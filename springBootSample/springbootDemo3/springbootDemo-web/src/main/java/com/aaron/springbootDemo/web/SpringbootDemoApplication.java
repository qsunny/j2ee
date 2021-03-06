package com.aaron.springbootDemo.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
 */
@RestController
@SpringBootApplication(scanBasePackages = {"com.aaron.springbootDemo.common","com.aaron.springbootDemo.core","com.aaron.springbootDemo.web","com.allchips.common"})
//@MapperScan("com.aaron.springbootDemo.dao")
public class SpringbootDemoApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.setProperty("user.timezone","Asia/Shanghai");
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }
}
