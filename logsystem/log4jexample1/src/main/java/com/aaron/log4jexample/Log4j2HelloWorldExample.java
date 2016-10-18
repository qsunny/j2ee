package com.aaron.log4jexample;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by aaron.qiu on 2016/10/17.
 */
public class Log4j2HelloWorldExample {
    private static final Logger LOGGER = LogManager.getLogger(Log4j2HelloWorldExample.class.getName());

    public static void main(String[] args)
    {
        LOGGER.debug("Debug Message Logged !!!");
        LOGGER.info("Info Message Logged !!!");
        LOGGER.error("Error Message Logged !!!", new NullPointerException("NullError"));
    }
}
