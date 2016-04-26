package com.aaron.bean.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HelloConfig.class,GoodbyeConfig.class,StandaloneDataConfig.class})
@ComponentScan(basePackages = "com.aaron")
public class AppConfig {
	
    
}
