package com.aaron;

import com.aaron.tools.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 @BeforeEach & @AfterEach & @BeforeAll & @AfterAll

 @BeforeEach 在每个测试方法运行之前执行一次。
 @AfterEach 在每个测试方法运行之后执行一次。
 @BeforeAll 在所有测试方法之前执行一次。
 @AfterAll 在所有测试方法之前执行一次。
 */
@SpringBootTest()
public class DemoTest5 {



    @Test
    public void demoTest() {
        System.out.println("test success");
    }

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void testAddition() {
        System.out.println("Test success");
    }


    @AfterEach
    public void tearDown() {
        System.out.println("@AfterEach");
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("@AfterAll");
    }

}
