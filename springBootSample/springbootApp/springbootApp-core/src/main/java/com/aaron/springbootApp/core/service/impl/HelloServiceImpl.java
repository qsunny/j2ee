package com.aaron.springbootApp.core.service.impl;

import com.aaron.springbootApp.core.service.HelloSerivce;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/25.
 */
@Service
public class HelloServiceImpl implements HelloSerivce{
    @Override
    public String sayHello(String name) {
        return "Hello,"+name;
    }
}
