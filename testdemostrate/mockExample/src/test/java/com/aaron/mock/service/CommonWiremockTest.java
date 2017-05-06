package com.aaron.mock.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Aaron.Qiu on 2017/5/6.
 * reference : http://wiremock.org/docs/getting-started/
 */
public class CommonWiremockTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHttpResponse() throws Exception {

    }




}
