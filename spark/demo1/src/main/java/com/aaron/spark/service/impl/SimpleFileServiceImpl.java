package com.aaron.spark.service.impl;

import com.aaron.spark.service.ISparkService;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class SimpleFileServiceImpl implements ISparkService {
    @Override
    public void laodFile() {
        String logFile = "C:\\Users\\Administrator\\Desktop\\product-error.log"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().master("local[2]").appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
