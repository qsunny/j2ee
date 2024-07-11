package com.aaron;

import com.aaron.tools.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @ParameterizedTest
    @MethodSource("provideAdditionTestData")
    public void testAddition(int a, int b, int expected) {
        Calculator calculator = new Calculator();
        int result = calculator.add(a, b);
        assertEquals(expected, result);
    }

//    private static Stream<Arguments> provideAdditionTestData() {
//        return Stream.of(
//                Arguments.of(1, 2, 3),
//                Arguments.of(5, -3, 2),
//                Arguments.of(10, 10, 20)
//        );
//    }

    enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    @ParameterizedTest
    @EnumSource(Operation.class)
    public void testCalculate(Operation operation) {
        Calculator calculator = new Calculator();

        int result;
        switch (operation) {
            case ADD:
                result = calculator.add(2, 3);
                assertEquals(5, result);
                break;
            case SUBTRACT:
                result = calculator.subtract(5, 2);
                assertEquals(3, result);
                break;
            case MULTIPLY:
                result = calculator.multiply(4, 6);
                assertEquals(24, result);
                break;
            case DIVIDE:
                result = calculator.divide(10, 2);
                assertEquals(5, result);
                break;
        }
    }
}
