package com.aaron.springbootApp.web.controller;

import com.aaron.springbootApp.core.service.HelloSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aaron.qiu on 2016/11/25.
 */
@RestController
public class HelloController {

    @Autowired
    private HelloSerivce helloService;

    @RequestMapping("/")
    String home() {
        return helloService.sayHello("Aaron!");
    }
}
