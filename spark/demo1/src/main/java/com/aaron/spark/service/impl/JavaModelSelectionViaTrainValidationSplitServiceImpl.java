package com.aaron.spark.service.impl;

import com.aaron.spark.service.ISparkService;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.tuning.ParamGridBuilder;
import org.apache.spark.ml.tuning.TrainValidationSplit;
import org.apache.spark.ml.tuning.TrainValidationSplitModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JavaModelSelectionViaTrainValidationSplitServiceImpl implements ISparkService {

    @Override
    public void laodFile() {
        SparkSession spark = SparkSession
                .builder()
                .master("local[2]")
                .appName("JavaModelSelectionViaCrossValidationExample")
                .getOrCreate();

        Dataset<Row> data = spark.read().format("libsvm")
                .load("src/main/resources/data/sample_linear_regression_data.txt");

    // Prepare training and test data.
        Dataset<Row>[] splits = data.randomSplit(new double[] {0.9, 0.1}, 12345);
        Dataset<Row> training = splits[0];
        Dataset<Row> test = splits[1];

        LinearRegression lr = new LinearRegression();

    // We use a ParamGridBuilder to construct a grid of parameters to search over.
    // TrainValidationSplit will try all combinations of values and determine best model using
    // the evaluator.
        ParamMap[] paramGrid = new ParamGridBuilder()
                .addGrid(lr.regParam(), new double[] {0.1, 0.01})
                .addGrid(lr.fitIntercept())
                .addGrid(lr.elasticNetParam(), new double[] {0.0, 0.5, 1.0})
                .build();

    // In this case the estimator is simply the linear regression.
    // A TrainValidationSplit requires an Estimator, a set of Estimator ParamMaps, and an Evaluator.
        TrainValidationSplit trainValidationSplit = new TrainValidationSplit()
                .setEstimator(lr)
                .setEvaluator(new RegressionEvaluator())
                .setEstimatorParamMaps(paramGrid)
                .setTrainRatio(0.8)  // 80% for training and the remaining 20% for validation
                .setParallelism(2);  // Evaluate up to 2 parameter settings in parallel

    // Run train validation split, and choose the best set of parameters.
        TrainValidationSplitModel model = trainValidationSplit.fit(training);

    // Make predictions on test data. model is the model with combination of parameters
    // that performed best.
        model.transform(test)
                .select("features", "label", "prediction")
                .show();

        spark.close();
    }
}
