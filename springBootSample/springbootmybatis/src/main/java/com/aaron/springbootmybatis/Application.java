package com.aaron.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Aaron.qiu on 2017/9/2.
 */
@SpringBootApplication
@MapperScan("com.aaron.springbootmybatis.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
