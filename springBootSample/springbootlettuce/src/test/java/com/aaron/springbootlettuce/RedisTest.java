package com.aaron.springbootlettuce;

import com.aaron.springbootlettuce.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testPutStr() {
        redisService.putRedisString("test","test test",30, TimeUnit.SECONDS);
        String value = redisService.getRedisString("test");
        System.out.println(value);
    }

}
