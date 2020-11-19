/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aaron.example;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class JavaWordCount {
  private static final Pattern SPACE = Pattern.compile(" ");

  public static void main(String[] args) throws Exception {

//    if (args.length < 1) {
//      System.err.println("Usage: JavaWordCount <file>");
//      System.exit(1);
//    }

    SparkSession spark = SparkSession
      .builder()
      .appName("JavaWordCount")
      .master("spark://192.168.0.107:7077")
      .config("spark.submit.deployMode", "cluster") // client cluster
//      .config("spark.driver.bindAddress", "192.168.1.37")
//      .config("spark.driver.blockManager.port", "53042")
      .config("spark.driver.host", "192.168.1.37")
      .config("spark.driver.port", "54052")
      .config("spark.executor.memory", "1024M")
//      .config("spark.worker.cleanup.enabled", "true")
      .config("spark.jars", "D:\\workspaceidea\\spark\\sparkExample1\\build\\libs\\sparkExample1-1.0.0-SNAPSHOT.jar")
      .getOrCreate();

    JavaRDD<String> lines = spark.read().textFile("hdfs://192.168.0.107:9001/user/allchips/books/redis.log").javaRDD();
//    JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();

    System.out.println("========start==========");
    JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
    System.out.println("==============dddd=====");

    JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

    JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

    List<Tuple2<String, Integer>> output = counts.collect();
    for (Tuple2<?,?> tuple : output) {
      System.out.println(tuple._1() + ": " + tuple._2());
    }
    System.out.println("===========end==============");
    spark.stop();
  }
}
