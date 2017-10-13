package com.example.springboot.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestConfig {

    @Autowired
    private GlobalProperties globalProperties;
    @Autowired
    private GlobalPropertiesByConfiguration globalPropertiesByConfiguration;
    @Autowired
    private AppProperties appProperties;


    @Test
    public void testLoadContent() {

    }

    @Test
    public void testGlobalProperties() {
        Assert.assertEquals("test@aaron.com",globalProperties.getEmail());
    }

    @Test
    public void testGlobalPropertiesByConfiguration() {
        Assert.assertEquals("test@aaron.com",globalPropertiesByConfiguration.getEmail());
    }

    @Test
    public void testAppProperties() {
        System.out.println(appProperties.toString());
    }
}
