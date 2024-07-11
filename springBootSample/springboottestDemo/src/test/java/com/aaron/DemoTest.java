package com.aaron;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @SpringBootTest(properties = {"my.property=value1", "my.otherProperty=value2"})
 * @SpringBootTest(classes = {MyConfig1.class, MyConfig2.class})
 * @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(value = "my.application.property=value")
public class DemoTest {

    @Value("${my.application.property}")
    private String value;

    @Test
    public void demoTest() {
        System.out.println("value:"+value);
        System.out.println("test success");
    }

}
