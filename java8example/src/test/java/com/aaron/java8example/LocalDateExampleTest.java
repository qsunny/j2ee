package com.aaron.java8example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LocalDateExampleTest {

    private LocalDateExample localDateExample;

    @Before
    public void setUp() throws Exception {
        localDateExample = new LocalDateExample();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringConvertLocalDate() {
        localDateExample.stringConvertLocalDate();
    }
}