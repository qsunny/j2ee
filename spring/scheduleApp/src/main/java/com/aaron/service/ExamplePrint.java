package com.aaron.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/25.
 */
@Service
public class ExamplePrint {

    @Scheduled(cron="*/5 * * * * ?")
    public void doSomething() {
        System.out.println("schedule annotation test....");
    }
}
