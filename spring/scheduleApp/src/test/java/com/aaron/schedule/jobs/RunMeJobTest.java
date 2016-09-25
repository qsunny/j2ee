package com.aaron.schedule.jobs;

import com.aaron.config.AppConfig;
import com.aaron.schedule.tasks.RunMeTask;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class RunMeJobTest {

    @Autowired
    private RunMeTask runMeTask;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRunMeTask() {
        Assert.assertNotNull(runMeTask);
        runMeTask.printMe();
    }


    @Test
    public void test() throws Exception{
        Thread.sleep(100*1000);
    }

}