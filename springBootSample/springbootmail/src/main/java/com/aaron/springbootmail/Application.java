package com.aaron.springbootmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Aaron.qiu on 2017/9/3.
 */
@SpringBootApplication(scanBasePackages = {"com.aaron.springbootmail"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
