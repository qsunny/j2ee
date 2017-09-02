package com.aaron.tools.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FreeMarkerHelperTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void process() {
        FreeMarkerHelper freeMarkerHelper =  new FreeMarkerHelper();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("name","aaron");
        String result = freeMarkerHelper.putStringTemplate("test","Name : ${name}").process("test",dataMap);
        System.out.println(result);

        Map<String,Object> dataMap2 = new HashMap<>();
        dataMap2.put("location", "Ayodhya");
        result = freeMarkerHelper.putStringTemplate("locationTemplate", "Location: ${location}").process("locationTemplate",dataMap2);
        System.out.println(result);
    }

}