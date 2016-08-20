package com.aaron.junit5example;

import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


/**
 * Created by aaron.qiu on 2016/8/20.
 */
@RunWith(JUnitPlatform.class)
public class Junit5Test {

    private static UnderTest unitUnderTest;

    @BeforeAll
    static void initializeExternalResources() {
        System.out.println("Initializing external resources...");
        unitUnderTest = new UnderTest();
    }

    @BeforeEach
    void initializeMockObjects() {
        System.out.println("Initializing mock objects...");
    }

    @Test
    @DisplayName("junit add display name for more detail description!")
    void someTest() {
        System.out.println("Running some test...");
        assertTrue(true);
    }

    @Test
    void otherTest() {
        assumeTrue(true);

        System.out.println("Running another test...");
        assertNotEquals(1, 42, "Why wouldn't these be the same?");
    }

    @Test
    void assertRelatedProperties() {
        List<String> numberAsString = Arrays.asList("Johannes", "Link","developer");

        assertAll("developer",
                () -> assertEquals("Marc", numberAsString.get(0)),
                () -> assertEquals("Philipp", numberAsString.get(1)),
                () -> assertEquals("developer", numberAsString.get(2))
        );
    }

    @Test
    @Disabled
    void disabledTest() {
        System.exit(1);
    }

    @Test
    void assertExceptions() {
        // assert that the method under test
        // throws the expected exception */
        assertThrows(Exception.class, unitUnderTest::methodUnderTest);

        Exception exception = expectThrows(
                Exception.class,
                unitUnderTest::methodUnderTest);
        assertEquals("This shouldn't happen.", exception.getMessage());
    }

    @Test
    void exitIfFalseIsTrue() {
        assumeTrue(false);
        System.exit(1);
    }

    @Test
    void exitIfTrueIsFalse() {
        assumeFalse(this::truism);
        System.exit(1);
    }

    private boolean truism() {
        return true;
    }

    @Test
    void exitIfNullEqualsString() {
        assumingThat(
                // state an assumption (a false one in this case) ...
                "null".equals(null),
                // … and only execute the lambda if it is true
                () -> System.exit(1)
        );
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tearing down...");
    }

    @AfterAll
    static void freeExternalResources() {
        System.out.println("Freeing external resources...");
    }

}
