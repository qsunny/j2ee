package com.aaron.zookeeper.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/9/21.
 */
@Configuration
@ComponentScan(basePackages = {"com.aaron.zookeeper.core.*"})
public class AppConfig {
}
