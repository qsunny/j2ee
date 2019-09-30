package com.aaron.spark.mllib;

import com.aaron.spark.BaseSparkTest;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import scala.collection.Seq;
import scala.collection.mutable.ArraySeq;

import java.util.Arrays;


public class FeatureTest extends BaseSparkTest {

    @Test
    @DisplayName("Feature Encoding Made Simple")
    public void featureEncodingTest() {
        Pipeline pipeline = new Pipeline("myPipeline");

    }



}
