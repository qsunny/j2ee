package com.aaron.java8example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/16.
 */
public class StreamExampleTest {

    private StreamExample streamExample;

    @Before
    public void setUp() throws Exception {
        streamExample = new StreamExample();
    }

    @Test
    public void testSteamDemostrate1() {
        streamExample.steamDemostrate1();
    }

    @Test
    public void testStreamFilterNull() {
        streamExample.streamFilterNull();
    }

    @Test
    public void testStreamFilterCondition() {
        streamExample.streamFilterCondition();
    }
}