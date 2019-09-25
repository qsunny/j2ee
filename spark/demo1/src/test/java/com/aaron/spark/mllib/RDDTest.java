package com.aaron.spark.mllib;


import com.aaron.spark.BaseTest;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.mllib.util.MLUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class RDDTest extends BaseTest {

    @Test
    @DisplayName("统计测试")
    public void statisticsTest() {

        // $example on$
        JavaRDD<Vector> mat = jsc.parallelize(
                Arrays.asList(
                        Vectors.dense(1.0, 10.0, 100.0),
                        Vectors.dense(2.0, 20.0, 200.0),
                        Vectors.dense(3.0, 30.0, 300.0)
                )
        ); // an RDD of Vectors

        // Compute column summary statistics.
        MultivariateStatisticalSummary summary = Statistics.colStats(mat.rdd());
        System.out.println(summary.mean());  // a dense vector containing the mean value for each column
        System.out.println(summary.variance());  // column-wise variance
        System.out.println(summary.numNonzeros());  // number of nonzeros in each column
        // $example off$

        jsc.stop();

    }

    @Test
    public void loadLabeledPointTest() {
        JavaRDD<LabeledPoint> examples =
                MLUtils.loadLibSVMFile(jsc.sc(), BASE_RESOURCE_PATH+"data/mllib/sample_libsvm_data.txt").toJavaRDD();
        examples.collect().stream().forEach(labeledPoint -> {
            System.out.println(labeledPoint);
        });
    }
}
