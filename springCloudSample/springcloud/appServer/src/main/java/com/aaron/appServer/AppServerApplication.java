package com.aaron.appServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/5.
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class AppServerApplication {
    private static Logger logger = LoggerFactory.getLogger(AppServerApplication.class);

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value="/greeting")
    public String greet() {
        logger.info("access /greeting");
        List<String> greetings = Arrays.asList("hi there","Greetings","Salutations");
        Random random = new Random();
        int randomNum = random.nextInt(greetings.size());
        return serverPort + ":" + greetings.get(randomNum);
    }

    @RequestMapping(value="/")
    public String home() {
        logger.info("Access /");
        return "Hi!";
    }

    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class,args);
    }

}
