package com.aaron.spark.service.impl;

import com.aaron.spark.bean.JavaDocument;
import com.aaron.spark.bean.JavaLabeledDocument;
import com.aaron.spark.service.ISparkService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.feature.*;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.tuning.CrossValidator;
import org.apache.spark.ml.tuning.CrossValidatorModel;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureExtracting4EncapsulationServiceImpl implements ISparkService {
    @Override
    public void laodFile() {
        SparkSession spark = SparkSession.builder().master("local[2]").appName("Extracting Feature4Encapsulation").getOrCreate();
        List<String> logData = spark.read().textFile("src/main/resources/encapsulation.dic").collectAsList();
        List<Row> rowList = new ArrayList<>();
        List<JavaLabeledDocument> javaLabeledDocumentList = new ArrayList<>();
        for(long i=0,size=logData.size();i<size;i++) {
            String word = logData.get((int) i);
            javaLabeledDocumentList.add(new JavaLabeledDocument(i, word, 1.0));
        }

        Dataset<Row> training = spark.createDataFrame(javaLabeledDocumentList, JavaLabeledDocument.class);
        training.show(10);

        // Configure an ML pipeline, which consists of three stages: tokenizer, hashingTF, and lr.
        Tokenizer tokenizer = new Tokenizer()
                .setInputCol("text")
                .setOutputCol("words");
        HashingTF hashingTF = new HashingTF()
                .setNumFeatures(1000)
                .setInputCol(tokenizer.getOutputCol())
                .setOutputCol("features");
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.01);
        Pipeline pipeline = new Pipeline()
                .setStages(new PipelineStage[] {tokenizer, hashingTF, lr});

        // We use a ParamGridBuilder to construct a grid of parameters to search over.
        // With 3 values for hashingTF.numFeatures and 2 values for lr.regParam,
        // this grid will have 3 x 2 = 6 parameter settings for CrossValidator to choose from.
        ParamMap[] paramGrid = new ParamGridBuilder()
                .addGrid(hashingTF.numFeatures(), new int[] {10, 100, 1000})
                .addGrid(lr.regParam(), new double[] {0.1, 0.01})
                .build();

        // We now treat the Pipeline as an Estimator, wrapping it in a CrossValidator instance.
        // This will allow us to jointly choose parameters for all Pipeline stages.
        // A CrossValidator requires an Estimator, a set of Estimator ParamMaps, and an Evaluator.
        // Note that the evaluator here is a BinaryClassificationEvaluator and its default metric
        // is areaUnderROC.
        CrossValidator cv = new CrossValidator()
                .setEstimator(pipeline)
//                .setEvaluator(new BinaryClassificationEvaluator())
                .setEvaluator(new RegressionEvaluator())
                .setEstimatorParamMaps(paramGrid)
                .setNumFolds(2)  // Use 3+ in practice
                .setParallelism(2);  // Evaluate up to 2 parameter settings in parallel

        // Run cross-validation, and choose the best set of parameters.
        CrossValidatorModel cvModel = cv.fit(training);

        // Prepare test documents, which are unlabeled.
        Dataset<Row> test = spark.createDataFrame(Arrays.asList(
                new JavaDocument(4L, "spark"),
                new JavaDocument(5L, "hhhh"),
                new JavaDocument(6L, "mapreduce spark"),
                new JavaDocument(7L, "apache hadoop")
        ), JavaDocument.class);

        // Make predictions on test documents. cvModel uses the best model found (lrModel).
        Dataset<Row> predictions = cvModel.transform(test);
        for (Row r : predictions.select("id", "text", "probability", "prediction").collectAsList()) {
            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> prob=" + r.get(2)
                    + ", prediction=" + r.get(3));
        }


        // $example off$

        spark.stop();
    }
}
