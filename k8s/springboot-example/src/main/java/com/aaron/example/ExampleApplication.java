package com.aaron.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExampleApplication {
    @RequestMapping("/")
    String home() {
        return "Hello World k8s roll update version:2.0.0!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
