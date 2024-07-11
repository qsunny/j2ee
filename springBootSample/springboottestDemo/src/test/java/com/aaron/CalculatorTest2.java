package com.aaron;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Disabled("This test is currently disabled")
public class CalculatorTest2 {

    @Test
    public void testAddition() {

    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testAddition2() {
        // 执行需要在5秒内完成的测试代码
    }
}
