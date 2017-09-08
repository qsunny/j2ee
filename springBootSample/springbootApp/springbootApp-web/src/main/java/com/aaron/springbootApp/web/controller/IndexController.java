package com.aaron.springbootApp.web.controller;


import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.BlogProperties;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.exception.SpringbootAppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

/**
 * Created by aaron.qiu on 2016/11/26.
 */
@Controller
public class IndexController {

    private static  final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private BlogProperties blogProperties;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @Value("${app.description:defualt descript value}")
    private String description;


    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model) throws SpringbootAppException {
        System.out.println("==========welcome============");

        log.info(blogProperties.toString());

        User u = userService.getUserById("22123fjdkfjdkfjse");
        System.out.println("u========="+u);
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("description",description);
        return "welcome";
    }




}
