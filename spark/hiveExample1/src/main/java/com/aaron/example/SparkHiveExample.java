package com.aaron.example;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
// $example off:spark_hive$

public class SparkHiveExample {

    // $example on:spark_hive$
    public static class Record implements Serializable {
        private int key;
        private String value;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    // $example off:spark_hive$

    public static void main(String[] args) {
        // $example on:spark_hive$
        // warehouseLocation points to the default location for managed databases and tables
//        String warehouseLocation = new File("spark-warehouse").getAbsolutePath();
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Spark Hive Example")
                .master("spark://192.168.0.107:7077")
                .config("spark.submit.deployMode", "client") // client cluster
                .config("spark.driver.host", "192.168.1.37")
                .config("spark.driver.port", "54052")
                .config("spark.executor.memory", "1024M")
                .config("spark.sql.warehouse.dir", "hdfs://192.168.0.107:9001/user/hive/warehouse")
                .config("spark.sql.warehouse.dir", "/user/hive/warehouse")
                .config("hive.metastore.uris", "thrift://192.168.0.107:9083")
                .enableHiveSupport()
                .getOrCreate();

        spark.sql("show databases").show();
//        Dataset<Row> jdbcDF = spark.read()
//                .format("jdbc")
//                .option("driver", "org.apache.hive.jdbc.HiveDriver")
//                .option("url", "jdbc:hive2://192.168.0.107:10000/test_product")
//                .option("dbtable", "t_ware_price")
//                .option("user", "hive")
//                .option("password", "hive")
////                .option("spark.sql.hive.metastore.version","2.3.7")
//                .load();

//        jdbcDF.sqlContext().sql("select * from t_ware_price limit 10").show();
        spark.sql("select * from test_product.t_ware_price limit 10").show();


        //连接方式一
/*        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "local_dba");
        connectionProperties.put("password", "local-67dba67");
//        connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver");
        connectionProperties.put("driver", "com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://192.168.0.101:3306/allchips_bom?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
        spark.read()
                .jdbc(url, "t_tw_bom", connectionProperties).select("id").limit(10).show();*/

        //连接方式二
/*        Dataset<Row> jdbcDF = spark.read()
        .format("jdbc")
        .option("url", "jdbc:mysql://192.168.0.101:3306/allchips_bom")
        .option("driver", "com.mysql.jdbc.Driver")
        .option("dbtable", "t_tw_bom")
        .option("user", "local_dba")
        .option("password", "local-67dba67")
        .load();

        jdbcDF.show();
        jdbcDF.select("id","record_logic_code").limit(100).show();*/

        spark.stop();
    }
}

