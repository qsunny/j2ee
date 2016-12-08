package com.aaron.springbootApp.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Aaron.qiu on 2016/12/5.
 */
@Configuration
@ImportResource("classpath*:springbootApp_dubbo.xml")
public class DubboConfig {
}
