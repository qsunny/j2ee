package com.aaron.spark.mllib;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.util.MLUtils;
import org.junit.jupiter.api.Test;

public class DataTypeTest {

    @Test
    public void localVectorTest() {
        // Create a dense vector (1.0, 0.0, 3.0).
        Vector dv = Vectors.dense(1.0, 0.0, 3.0);
        System.out.println(dv);
        // Create a sparse vector (1.0, 0.0, 3.0) by specifying its indices and values corresponding to nonzero entries.
        Vector sv = Vectors.sparse(3, new int[] {0, 2}, new double[] {1.0, 3.0});
        System.out.println(sv);
    }

    @Test
    public void LabeledPointTest() {
        // Create a labeled point with a positive label and a dense feature vector.
        LabeledPoint pos = new LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0));
        System.out.println(pos.asML());
        // Create a labeled point with a negative label and a sparse feature vector.
        LabeledPoint neg = new LabeledPoint(0.0, Vectors.sparse(3, new int[] {0, 2}, new double[] {1.0, 3.0}));
        System.out.println(neg.asML());
    }


}
