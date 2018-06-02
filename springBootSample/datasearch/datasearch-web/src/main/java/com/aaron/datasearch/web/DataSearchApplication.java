package com.aaron.datasearch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Aaron.qiu on 2018/6/2.
 */
@SpringBootApplication(scanBasePackages = {"com.aaron.datasearch.core","com.aaron.datasearch.web","com.aaron.datasearch.common"})
public class DataSearchApplication {
    public static void main(String[] args) throws Exception {
        System.setProperty("user.timezone","Asia/Shanghai");

        SpringApplication.run(DataSearchApplication.class, args);
    }

}
