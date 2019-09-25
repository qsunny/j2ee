package com.aaron.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected JavaSparkContext jsc;
    protected String BASE_RESOURCE_PATH = "src/test/resources/";
    @BeforeEach
    public void before() {
        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("JavaSummaryStatisticsExample");
        jsc = new JavaSparkContext(conf);
    }

    @AfterEach
    public void after() {
        jsc.close();
    }


}
