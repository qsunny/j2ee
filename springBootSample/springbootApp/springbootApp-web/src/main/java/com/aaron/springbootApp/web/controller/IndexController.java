package com.aaron.springbootApp.web.controller;


import com.aaron.springbootApp.bean.User;
import com.aaron.springbootApp.core.service.IUserService;
import com.aaron.springbootApp.exception.SpringbootAppException;
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

    @Autowired
    private IUserService userService;

    @Value("${application.message:Hello World}")
    private String message = "Hello World";



    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model) throws SpringbootAppException {
        System.out.println("==========welcome============");
        User u = userService.getUserById("22123fjdkfjdkfjse");
        System.out.println("u========="+u);
        model.put("time", new Date());
        model.put("message", this.message);
        return "welcome";
    }

}
