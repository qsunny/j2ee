package com.aaron.springbootDemo.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Aaron.qiu on 2020-10-30.
 */
@Configuration
@ImportResource("classpath*:springbootdemo_dubbo.xml")
public class DubboConfig {
}
