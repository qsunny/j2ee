package com.aaron;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.aaron.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @SpringBootTest(properties = {"my.property=value1", "my.otherProperty=value2"})
 * @SpringBootTest(classes = {MyConfig1.class, MyConfig2.class})
 * @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 */
@SpringBootTest(classes = MybatisConfig.class)
public class DemoTest2 {

    @Resource
    private MybatisConfig config;

    @Test
    public void demoTest() {
        System.out.println("test success");
        System.out.println(config.initBean());
    }

}
