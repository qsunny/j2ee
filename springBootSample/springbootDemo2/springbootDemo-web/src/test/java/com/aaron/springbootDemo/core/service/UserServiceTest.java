package com.aaron.springbootDemo.core.service;

import com.aaron.springbootDemo.bean.User;
import com.aaron.springbootDemo.core.service.user.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@MybatisTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void selectUserByIdTest() throws Exception {
        User user = userService.getUserById("1");
        assertThat(user.getName()).isEqualTo("保罗");
        assertThat(user.getAge()).isEqualTo(26);
        assertThat(user.getBalance()).isEqualTo(15241);
    }
}
