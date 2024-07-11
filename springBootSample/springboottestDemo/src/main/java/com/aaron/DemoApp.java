package com.aaron;

import com.aaron.bean.User;
import com.aaron.config.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@EnableScheduling
@SpringBootApplication(scanBasePackages={"com.aaron"})
public class DemoApp {

    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    @Resource
    private MybatisConfig config;

    //http://localhost:8888/demo-app/test/start
    @RestController
    public class DruidStatController {
        @GetMapping("/test/start")
        public User druidStat(){
            User user = new User();
            user.setUserName("SpringbootDemo")
                    .setUserId(1L);
            return config.initBean();
        }
    }
}
