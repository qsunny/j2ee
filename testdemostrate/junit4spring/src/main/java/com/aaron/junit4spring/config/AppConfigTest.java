package com.aaron.junit4spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

/**
 * Created by Administrator on 2016/8/20.
 */
@Configuration
@PropertySource(value="classpath:config.properties")
public class AppConfigTest {

    @Value("#{'${server.name}'.split(',')}")
    private List<String> servers;

    @Value("#{'${server.id}'.split(',')}")
    private List<Integer> serverId;

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void serverInfo() {
        System.out.println(servers.size());
        for(String temp : servers){
            System.out.println(temp);
        }

        System.out.println(serverId.size());
        for(Integer temp : serverId){
            System.out.println(temp);
        }
    }
}
