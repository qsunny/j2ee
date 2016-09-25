package com.aaron.schedule.tasks.impl;

import com.aaron.schedule.tasks.RunMeTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/9/25.
 */
@Service
public class RunMeTaskImpl implements RunMeTask{

    Logger log = LoggerFactory.getLogger(RunMeTaskImpl.class);

    @Override
    public void printMe() {
        log.info("==========Spring4+Quartz2.2 demostrate");
        System.out.println("Spring 4 + Quartz 2.2 ~");
    }

    @Override
    public void printMe(String name) {
        log.info("==========Spring4+Quartz2.2 demostrate " + name);
    }
}
