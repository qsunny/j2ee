package com.aaron.springbootdocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    public String home() {
        logger.info("======home start=======");
        logger.info("======home end=======");
        int i = 1/0;
        return "Hello Docker World";
    }

    public static void main(String[] args) {
        logger.info("======home end=======");
        logger.trace("======application start=======");
        logger.error("======application error=======");
        SpringApplication.run(Application.class, args);
    }
}
