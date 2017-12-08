package com.aaron.joddlibs.bean;

import jodd.bean.BeanUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * https://jodd.org/beanutil/
 */
public class FooTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getReadwrite() throws Exception {
        Foo foo = new Foo();
        BeanUtil.pojo.setProperty(foo, "readwrite", "data");
        BeanUtil.pojo.getProperty(foo, "readwrite");
        BeanUtil.declared.setProperty(foo, "readonly", "data");
        System.out.println(foo);
    }



}