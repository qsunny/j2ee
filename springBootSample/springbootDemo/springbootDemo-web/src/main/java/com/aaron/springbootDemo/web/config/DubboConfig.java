package com.aaron.springbootDemo.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Aaron.qiu on 2019-01-26.
 */
@Configuration
@ImportResource("classpath*:springbootDemo_dubbo.xml")
public class DubboConfig {
}
