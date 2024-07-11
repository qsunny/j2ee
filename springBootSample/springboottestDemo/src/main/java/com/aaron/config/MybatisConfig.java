package com.aaron.config;

import com.aaron.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public User initBean() {
        User user = new User();
        user.setUserName("mybatis");
        return user;
    }

}
