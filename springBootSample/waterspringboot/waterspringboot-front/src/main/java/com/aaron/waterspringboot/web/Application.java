package com.aaron.waterspringboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
