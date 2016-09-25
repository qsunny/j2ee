package com.aaron.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrator on 2016/9/25.
 */
@Configuration
@Import({ScheduleConfig.class,ScheduleConfigV2.class})
public class AppConfig {
}
