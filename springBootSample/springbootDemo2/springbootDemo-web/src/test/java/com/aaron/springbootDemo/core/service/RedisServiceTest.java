package com.aaron.springbootDemo.core.service;

import com.aaron.springbootDemo.bean.User;
import com.aaron.springbootDemo.core.service.redis.RedisService;
import com.aaron.springbootDemo.utils.CommUtils;
import com.aaron.springbootDemo.utils.JsonUtil;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import com.aaron.springbootDemo.web.SpringbootDemoApp;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootDemoApp.class})
public class RedisServiceTest {
    @Resource
    private RedisService redisService;

    @Test
    public void test1() {
        Assert.assertNotNull(redisService);
    }

    @ClassRule
    public static OutputCaptureRule out = new OutputCaptureRule();

    @Test
    public void contextLoads() {
        out.expect(containsString("1,San Francisco,CA,US"));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setAge(111);
        user.setBalance(10L);
        user.setId(11L);
        user.setName("aaron");
        redisService.put("userTest", JsonUtil.toJson(user),120, TimeUnit.SECONDS);
    }

    @Test
    public void test3() {
        String jsonUser = redisService.get("userTest");
        if(CommUtils.isNotEmpty(jsonUser)) {
            User u = JsonUtil.fromJson(jsonUser,User.class);
            System.out.println("================================");
            System.out.println(u);
        }
    }
}
