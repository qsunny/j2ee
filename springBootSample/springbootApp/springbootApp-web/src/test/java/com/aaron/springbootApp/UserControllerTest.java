package com.aaron.springbootApp;

import com.aaron.springbootApp.core.service.HelloSerivce;
import com.aaron.springbootApp.web.SpringBootApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * Created by Aaaron.Qiu on 2016/12/3.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private HelloSerivce helloSerivce;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getHello() throws Exception {
        Assert.assertEquals("Hello, Aaron",helloSerivce.sayHello("Aaron"));
    }

}
