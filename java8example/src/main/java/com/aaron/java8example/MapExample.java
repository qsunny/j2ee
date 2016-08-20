package com.aaron.java8example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MapExample {

    public void mapDemostrate1() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        Map<String, Integer> result = new LinkedHashMap<>();

        //sort by value, and reserve, 10,9,8,7,6...
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);
    }

    public void mapDemostrate2() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        System.out.println("Sort By Key...");
        Map<String, Integer> resultKey = compareByKey(unsortMap);
        System.out.println(resultKey);

        System.out.println("Sort By Value...");
        Map<String, Integer> resultValue = compareByValue(unsortMap);
        System.out.println(resultValue);
    }

    //Reference from java.util.Map source code, try dig inside, many generic examples.
    public static <K, V extends Comparable<? super V>> Map<K, V> compareByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }

    public static <K extends Comparable<? super K>, V> Map<K, V> compareByKey(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> mapInStream = map.entrySet().stream();

        mapInStream.sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;

    }


}
