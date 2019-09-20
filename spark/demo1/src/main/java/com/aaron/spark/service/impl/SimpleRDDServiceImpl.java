package com.aaron.spark.service.impl;

import com.aaron.spark.service.ISparkService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class SimpleRDDServiceImpl implements ISparkService {

    @Override
    public void laodFile() {
        SparkConf conf = new SparkConf().setAppName("Sprak-RDD-APP").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> distData = sc.parallelize(data);
        int result = distData.first();
        System.out.println(result);
        sc.close();
    }
}
