package com.aaron.spark.service.impl;

import com.aaron.spark.service.ISparkService;
import org.apache.spark.ml.classification.MultilayerPerceptronClassificationModel;
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class MultilayerPerceptronClassifierServiceImpl implements ISparkService {
    @Override
    public void laodFile() {
        SparkSession spark = SparkSession.builder().master("local[2]").appName("Simple Application").getOrCreate();

        // Load training data
        String path = "src/main/resources/data/sample_multiclass_classification_data.txt";
        Dataset<Row> dataFrame = spark.read().format("libsvm").load(path);

        // Split the data into train and test
        Dataset<Row>[] splits = dataFrame.randomSplit(new double[]{0.6, 0.4}, 1234L);
        Dataset<Row> train = splits[0];
        Dataset<Row> test = splits[1];
        train.foreach((row)->{
            //System.out.println(row);
        });

        // specify layers for the neural network:
        // input layer of size 4 (features), two intermediate of size 5 and 4
        // and output of size 3 (classes)
        int[] layers = new int[] {4, 5, 4, 3};

        // create the trainer and set its parameters
        MultilayerPerceptronClassifier trainer = new MultilayerPerceptronClassifier()
                .setLayers(layers)
                .setBlockSize(128)
                .setSeed(1234L)
                .setMaxIter(100);

        // train the model
        MultilayerPerceptronClassificationModel model = trainer.fit(train);

        // compute accuracy on the test set
        Dataset<Row> result = model.transform(test);
        Dataset<Row> predictionAndLabels = result.select("prediction", "label");
        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
                .setMetricName("accuracy");

        System.out.println("Test set accuracy = " + evaluator.evaluate(predictionAndLabels));
    }
}
