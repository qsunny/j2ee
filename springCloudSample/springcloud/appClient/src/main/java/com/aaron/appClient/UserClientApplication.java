package com.aaron.appClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Administrator on 2017/3/5.
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@RibbonClient(name = "HelloService", configuration = RibbonConfig.class)
public class UserClientApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value="name",defaultValue = "Aaron")String name) {
        String greeting = this.restTemplate.getForObject("http://HelloService/greeting",String.class);
        return String.format("%s,%s",greeting,name);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class,args);
    }

}
