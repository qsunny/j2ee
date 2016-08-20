package com.aaron;

import com.aaron.junit4spring.config.AppConfigMongoDB;
import com.aaron.junit4spring.config.AppConfigMongoDB2;
import com.aaron.junit4spring.config.AppConfigTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class,AppConfigMongoDB.class,AppConfigMongoDB2.class})
public class TestSpringConfig {

    @Autowired
    private AppConfigTest appConfigTest;
    @Autowired
    private AppConfigMongoDB appConfigMongoDB;
    @Autowired
    private AppConfigMongoDB2 appConfigMongoDB2;


    @Before
    public void before() {

    }

    @Test
    public void printConfigInfo() {
        appConfigTest.serverInfo();
    }

    @Test
    public void mongodb1() {
        Assert.assertNotNull(appConfigMongoDB);
        Assert.assertNotNull(appConfigMongoDB2);
    }

}
