package com.aaron.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 没有测试通过
 * Created by Administrator on 2016/9/25.
 */
@Configuration
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = {"com.aaron.service.*"})
public class ScheduleConfigV2 {
}
