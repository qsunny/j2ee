package com.aaron;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @SpringBootTest(properties = {"my.property=value1", "my.otherProperty=value2"})
 * @SpringBootTest(classes = {MyConfig1.class, MyConfig2.class})
 * @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoTest3 {



    @Test
    public void demoTest() {
        System.out.println("test success");
    }

}
