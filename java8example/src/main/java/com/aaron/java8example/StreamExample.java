package com.aaron.java8example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    public void steamDemostrate3() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

    }

    public void steamDemostrate4() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

    // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

    }

    public void steamDemostrate5() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true

        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);    // 3

        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

    }

    public void streamFilterNull() {
        Stream<String> language = Stream.of("java", "python", "node", null, "ruby", null, "php");

        //List<String> result = language.collect(Collectors.toList());

        List<String> result = language.filter(x -> x!=null).collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    public void streamFilterCondition() {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("php");
        list.add("python");
        list.add("lisp");
        list.add("c++");

        //filter function
        Stream<String> stream = list.stream().filter(p -> p.length() > 3);
        String[] arr = stream.toArray(String[]::new);

        System.out.println(Arrays.toString(arr));
    }

    public static void convertStringArr() {
        String[] stringArr = { "a", "b", "c", "d" };
        Stream<String> stream = Stream.of(stringArr);
        String[] arr = stream.toArray(size -> new String[size]);
        System.out.println(Arrays.toString(arr));

        //String[] arr = stream.toArray(String[]::new);
    }

    public static  void mergeTwoStreams() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "e", "f", "g" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);

        Stream<String> stream3 = Stream.concat(stream1, stream2);
        String[] arr = stream3.toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static  void mergeStreams() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "e", "f", "g" };
        String[] arr3 = { "h", "i", "j" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        Stream<String> stream3 = Stream.of(arr3);

        //use Stream.of(T... values)
        Stream<String> stream = Stream.of(stream1, stream2, stream3).flatMap(x -> x);

        String[] arr = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static  void mergeFilteredStreamToOther() {
        String[] arr1 = { "abc", "bcd", "cdef", "defgh" };
        String[] arr2 = { "af", "fg", "gh" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);

        Stream<String> stream3 = Stream.concat(stream1.filter(x -> x.length()<4), stream2);
        String[] arr = stream3.toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergerMoreThan2Streams() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "e", "f", "g" };
        String[] arr3 = { "h", "i", "j" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        Stream<String> stream3 = Stream.of(arr3);

        Stream<String> stream = Stream.concat(Stream.concat(stream1, stream2), stream3);
        String[] arr = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(arr));
    }

}
