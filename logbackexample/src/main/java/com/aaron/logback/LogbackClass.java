package com.aaron.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackClass {
    private final static transient Logger logger = LoggerFactory.getLogger(LogbackClass.class);

    public static void main(String[] args) {
        
        logger.info("Hello from LogbackClass. Info");
        logger.warn("Hello from LogbackClass. Warn");
        logger.error("Hello from LogbackClass. Error");
        logger.debug("Hello from LogbackClass. Debug");
    }
}
