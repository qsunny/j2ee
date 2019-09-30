package com.aaron.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseSparkTest {
    protected SparkSession spark = null;
    protected String BASE_RESOURCE_PATH = "src/test/resources";
    @BeforeEach
    public void before() {
        spark = SparkSession.builder().master("local[2]").appName("Extracting Feature").getOrCreate();
    }

    @AfterEach
    public void after() {
        spark.stop();
    }


}
