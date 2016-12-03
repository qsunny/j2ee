package com.aaron.springbootApp.web.controller;

import com.aaron.springbootApp.core.service.HelloSerivce;
import com.aaron.springbootApp.exception.SpringbootAppException;
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

    @RequestMapping("/home")
    String home() {
        return helloService.sayHello("Aaron!");
    }
    @RequestMapping("/error")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/jsonError")
    public String json() throws SpringbootAppException {
        throw new SpringbootAppException("发生错误2");
    }

}
