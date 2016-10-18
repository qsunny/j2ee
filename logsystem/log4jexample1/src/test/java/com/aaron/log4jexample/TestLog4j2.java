package com.aaron.log4jexample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;


/**
 * Created by aaron.qiu on 2016/10/17.
 */
public class TestLog4j2 {
    private static Logger LOGGER = null;

    @BeforeClass
    public static void setLogger() throws MalformedURLException
    {
        System.setProperty("log4j.configurationFile","log4j2-testConfig.xml");
        LOGGER = LogManager.getLogger();
    }

    @Test
    public void testOne()
    {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }

}
