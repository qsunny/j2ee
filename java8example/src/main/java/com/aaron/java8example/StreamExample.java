package com.aaron.java8example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream demostrate
 * Created by aaron.qiu on 2016/8/16.
 */
public class StreamExample {

    public void steamDemostrate1() {
        Stream<String> language = Stream.of("java","python","nodejs");

        //convert a stream to list
        List<String> result = language.collect(Collectors.toList());
        result.forEach(System.out::println);

    }

    public void steamDemostrate2() {
        Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);

        List<Integer> result2 = number.filter(x -> x!= 3).collect(Collectors.toList());

        result2.forEach(x -> System.out.println(x));

    }

    public void streamFilterNull() {
        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");

        //List<String> result = language.collect(Collectors.toList());

        List<String> result = language.filter(x -> x!=null).collect(Collectors.toList());

        result.forEach(System.out::println);
    }


}
