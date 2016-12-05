package com.aaron.springbootApp.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Administrator on 2016/12/5.
 */
@Configuration
@ImportResource(locations = {"springbootApp_dubbo.xml","springbootApp_dubbo_customer.xml","springbootApp_dubbo_provider.xml"})
public class DubboConfig {
}
