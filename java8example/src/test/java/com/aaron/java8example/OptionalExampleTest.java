package com.aaron.java8example;

import com.aaron.java8example.date.LocalDateExample;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional is just a container: it can hold a value of some type T or just be null.
 * It provides a lot of useful methods so the explicit null checks have no excuse anymore.
 * The isPresent() method returns true if this instance of Optional has non-null value and false otherwise.
 * The orElseGet() method provides the fallback mechanism in case Optional has null value by accepting
 * the function to generate the default one. The map() method transforms the current Optional’s value and
 * returns the new Optional instance. The orElse() method is similar to orElseGet()
 * but instead of function it accepts the default value.
 */
public class OptionalExampleTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void optionalExample1Test() {
        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }

    @Test
    public void optionalExample2Test() {
        Optional< String > firstName = Optional.of( "Tom" );
        System.out.println( "First Name is set? " + firstName.isPresent() );
        System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) );
        System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
        System.out.println();
    }

    @Test
    public void optionalExample3Test() {
        Optional< String > fullName = Optional.ofNullable( null );
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }

}
