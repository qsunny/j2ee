package com.aaron.java8example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.*;

public class LambdaExampleTest {

    public LambdaExample lambdaExample= null;

    @Before
    public void setUp() throws Exception {
        lambdaExample = new LambdaExample();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRunnable() {
        new Thread(lambdaExample.r1).start();
        new Thread(lambdaExample.r2).start();

    }

    @Test
    public void testMethodReferences() {
        Function<String, String> upperfier = String::toUpperCase;
        String result = upperfier.apply("EdddddE");
        System.out.println(result);

        IntFunction<int[]> arrayMaker = int[]::new;
        int[] array = arrayMaker.apply(10); // 创建数组 int[10]
    }

    @Test
    public void testPredicate() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Boolean notNullFlag = nonNull.test(null);
        System.out.println(notNullFlag);

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

    }

    @Test
    public void testFunction() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        String result = backToString.apply("123");     // "123"
        System.out.println(result);
    }

    @Test
    public void testSupplier() {
        //Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<Person> personSupplier = Person::new;
        Person p = personSupplier.get();   // new Person
        p.setName("aaron");
        System.out.println(p.getName());

        Consumer<Person> greeter = (pp) -> System.out.println("Hello, " + pp.getName());
        greeter.accept(new Person("Luke"));
    }

    @Test
    public void testComparator() {
        Comparator<Person> comparator = (p1, p2) -> p1.getName().compareTo(p2.getName());

        Person p1 = new Person("John");
        Person p2 = new Person("Alice");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0
    }

    @Test
    public void testOptional() {
        Optional<String> optional = Optional.of("bam");


        System.out.println(optional.isPresent());           // true
        System.out.println(optional.get());                 // "bam"
        System.out.println(optional.orElse("fallback"));    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
    }

    @Test
    public void testComparator2() {
        Comparator<Person> comparator = (p1, p2) -> p1.getAge().compareTo(p2.getAge());
        Person p1 = new Person("aaron",27);
        Person p2 = new Person("andoni",29);

        System.out.println(comparator.compare(p1,p2));
    }

}
