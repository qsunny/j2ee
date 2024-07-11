package com.aaron;

import com.aaron.tools.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @ParameterizedTest用来标记一个参数化测试方法，和以下注解搭配使用。(列出常用的几种入参方式注解)
 *
 * @ValueSource： 用于指定基本类型、String类型或Class类型的参数值。
 * @CsvSource： 用于指定CSV格式的参数值，逗号分隔每个参数。
 * @MethodSource： 用于指定一个或多个方法作为参数提供者。
 * @ArgumentsSource： 用于指定一个自定义的参数提供者类。
 * @NullSource： 用于指定null值作为参数。
 * @EmptySource： 用于指定空值作为参数。
 * @NullAndEmptySource： 用于指定null值和空值作为参数。
 * @DisplayNameGeneration： 用于指定一个自定义的显示名称生成器。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoTest4 {



    @Test
    public void demoTest() {
        System.out.println("test success");
    }

    /**
     * @ParameterizedTest标记名为testIsPositive的参数化测试方法
     * @ValueSource注解，我们将整数1、2和3作为参数传递给测试方法
     * @param number
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testIsPositive(int number) {
        assertTrue(number > 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testIsPositive2(int number) {
        assertTrue(number > 0);
    }

    @ParameterizedTest
    @NullSource
    public void testIsEmpty_Null(String input) {
        assertTrue(StringUtils.isEmpty(input));
    }

    /**
     * @RepeatedTest(5) 标记名为testAddition的测试方法，并指定要重复运行它的次数为5次
     */
    @RepeatedTest(5)
    public void testAddition() {
        int result = 2 + 2;
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Addition Test")
    public void testAddition2() {
        int result = Calculator.add(2, 2);
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Subtraction Test")
    public void testSubtraction() {
        int result = Calculator.subtract(5, 3);
        assertEquals(2, result);
    }

}
