package com.aaron.spark.mllib;


import com.aaron.spark.BaseTest;
import org.apache.hadoop.hive.ql.exec.RecordReader;
import org.apache.hadoop.io.Writable;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.linalg.*;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.stat.KernelDensity;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.mllib.stat.test.*;
import org.apache.spark.mllib.util.KMeansDataGenerator;
import org.apache.spark.mllib.util.LinearDataGenerator;
import org.apache.spark.mllib.util.LogisticRegressionDataGenerator;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.mllib.linalg.distributed.IndexedRow;
import org.apache.spark.mllib.linalg.distributed.IndexedRowMatrix;
import org.apache.spark.mllib.linalg.distributed.RowMatrix;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Seconds;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.spark_project.guava.collect.ImmutableMap;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

import static org.apache.spark.mllib.random.RandomRDDs.normalJavaRDD;

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
                MLUtils.loadLibSVMFile(jsc.sc(), BASE_RESOURCE_PATH+"/data/mllib/sample_libsvm_data.txt").toJavaRDD();
        examples.collect().stream().forEach(labeledPoint -> {
            System.out.println(labeledPoint);
        });
    }

    @Test
    @DisplayName("分布式矩阵-RowMatrix")
    public void rowMatrixTest() {
        Vector dv = Vectors.dense(1.0, 0.0, 3.0);
        // a JavaRDD of local vectors
        JavaRDD<Vector> rows = jsc.parallelize(
            Arrays.asList(
                    Vectors.dense(1.0, 10.0, 100.0),
                    Vectors.dense(2.0, 20.0, 200.0),
                    Vectors.dense(3.0, 30.0, 300.0)
            )
        );
        // Create a RowMatrix from an JavaRDD<Vector>.
        RowMatrix mat = new RowMatrix(rows.rdd());
        System.out.println(mat);
        // Get its size.
        long m = mat.numRows();
        long n = mat.numCols();

        // QR decomposition
        QRDecomposition<RowMatrix, Matrix> result = mat.tallSkinnyQR(true);
        System.out.println(result);
    }

    @Test
    @DisplayName("Correlations")
    public void correlationsTest() {
        JavaDoubleRDD seriesX = jsc.parallelizeDoubles(
                Arrays.asList(1.0, 2.0, 3.0, 3.0, 5.0));  // a series

        // must have the same number of partitions and cardinality as seriesX
        JavaDoubleRDD seriesY = jsc.parallelizeDoubles(
                Arrays.asList(11.0, 22.0, 33.0, 33.0, 555.0));

        // compute the correlation using Pearson's method. Enter "spearman" for Spearman's method.
        // If a method is not specified, Pearson's method will be used by default.
        Double correlation = Statistics.corr(seriesX.srdd(), seriesY.srdd(), "pearson");
        System.out.println("Correlation is: " + correlation);

        // note that each Vector is a row and not a column
        JavaRDD<Vector> data = jsc.parallelize(
                Arrays.asList(
                        Vectors.dense(1.0, 10.0, 100.0),
                        Vectors.dense(2.0, 20.0, 200.0),
                        Vectors.dense(5.0, 33.0, 366.0)
                )
        );

        // calculate the correlation matrix using Pearson's method.
        // Use "spearman" for Spearman's method.
        // If a method is not specified, Pearson's method will be used by default.
        Matrix correlMatrix = Statistics.corr(data.rdd(), "pearson");
        System.out.println(correlMatrix.toString());
    }

    @Test
    @DisplayName("Stratified sampling")
    public void stratifiedSamplingTest() {
        List<Tuple2<Integer, Character>> list = Arrays.asList(
                new Tuple2<>(1, 'a'),
                new Tuple2<>(1, 'b'),
                new Tuple2<>(2, 'c'),
                new Tuple2<>(2, 'd'),
                new Tuple2<>(2, 'e'),
                new Tuple2<>(3, 'f')
        );

        JavaPairRDD<Integer, Character> data = jsc.parallelizePairs(list);

        // specify the exact fraction desired from each key Map<K, Double>
        ImmutableMap<Integer, Double> fractions = ImmutableMap.of(1, 0.1, 2, 0.6, 3, 0.3);

        // Get an approximate sample from each stratum
        JavaPairRDD<Integer, Character> approxSample = data.sampleByKey(false, fractions);
        // Get an exact sample from each stratum
        JavaPairRDD<Integer, Character> exactSample = data.sampleByKeyExact(false, fractions);

    }

    @Test
    @DisplayName("Hypothesis testing")
    public void hypothesisTestingTest() {
        // a vector composed of the frequencies of events
        Vector vec = Vectors.dense(0.1, 0.15, 0.2, 0.3, 0.25);

        // compute the goodness of fit. If a second vector to test against is not supplied
        // as a parameter, the test runs against a uniform distribution.
        ChiSqTestResult goodnessOfFitTestResult = Statistics.chiSqTest(vec);
        // summary of the test including the p-value, degrees of freedom, test statistic,
        // the method used, and the null hypothesis.
        System.out.println(goodnessOfFitTestResult + "\n");

        // Create a contingency matrix ((1.0, 2.0), (3.0, 4.0), (5.0, 6.0))
        Matrix mat = Matrices.dense(3, 2, new double[]{1.0, 3.0, 5.0, 2.0, 4.0, 6.0});

        // conduct Pearson's independence test on the input contingency matrix
        ChiSqTestResult independenceTestResult = Statistics.chiSqTest(mat);
        // summary of the test including the p-value, degrees of freedom...
        System.out.println(independenceTestResult + "\n");

        // an RDD of labeled points
        JavaRDD<LabeledPoint> obs = jsc.parallelize(
                Arrays.asList(
                        new LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0)),
                        new LabeledPoint(1.0, Vectors.dense(1.0, 2.0, 0.0)),
                        new LabeledPoint(-1.0, Vectors.dense(-1.0, 0.0, -0.5))
                )
        );

        // The contingency table is constructed from the raw (label, feature) pairs and used to conduct
        // the independence test. Returns an array containing the ChiSquaredTestResult for every feature
        // against the label.
        ChiSqTestResult[] featureTestResults = Statistics.chiSqTest(obs.rdd());
        int i = 1;
        for (ChiSqTestResult result : featureTestResults) {
            System.out.println("Column " + i + ":");
            System.out.println(result + "\n");  // summary of the test
            i++;
        }
    }

    @Test
    @DisplayName("Hypothesis testing Kolmogorov Smirnov")
    public void hypothesisTestingKolmogorovSmirnovTest() {
        JavaDoubleRDD data = jsc.parallelizeDoubles(Arrays.asList(0.1, 0.15, 0.2, 0.3, 0.25));
        KolmogorovSmirnovTestResult testResult =
                Statistics.kolmogorovSmirnovTest(data, "norm", 0.0, 1.0);
        // summary of the test including the p-value, test statistic, and null hypothesis
        // if our p-value indicates significance, we can reject the null hypothesis
        System.out.println(testResult);
    }

    private static int timeoutCounter = 0;
    @Test
    @DisplayName("Streaming Significance Testing")
    public void StreamingTest() throws InterruptedException {
        String dataDir = BASE_RESOURCE_PATH+"/data/mllib/stream_test.txt";
        Duration batchDuration = Seconds.apply(5);
        int numBatchesTimeout = 100;

        SparkConf conf = new SparkConf().setMaster("local").setAppName("StreamingTestExample");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, batchDuration);

        ssc.checkpoint(Utils.createTempDir(System.getProperty("java.io.tmpdir"), "spark").toString());

        // $example on$
        JavaDStream<BinarySample> data = ssc.textFileStream(dataDir).map(line -> {
            String[] ts = line.split(",");
            boolean label = Boolean.parseBoolean(ts[0]);
            double value = Double.parseDouble(ts[1]);
            return new BinarySample(label, value);
        });

        StreamingTest streamingTest = new StreamingTest()
                .setPeacePeriod(0)
                .setWindowSize(0)
                .setTestMethod("welch");

        JavaDStream<StreamingTestResult> out = streamingTest.registerStream(data);
        out.print();
        // $example off$

        // Stop processing if test becomes significant or we time out
        timeoutCounter = numBatchesTimeout;

        out.foreachRDD(rdd -> {
            timeoutCounter -= 1;
            boolean anySignificant = !rdd.filter(v -> v.pValue() < 0.05).isEmpty();
            if (timeoutCounter <= 0 || anySignificant) {
                rdd.context().stop();
            }
        });

        ssc.start();
        ssc.awaitTermination();
    }

    @Test
    @DisplayName("Random data generation")
    public void randomDataGenerationTest() {
        // Generate a random double RDD that contains 1 million i.i.d. values drawn from the
        // standard normal distribution `N(0, 1)`, evenly distributed in 10 partitions.
        JavaDoubleRDD u = normalJavaRDD(jsc, 1000L, 10);
        System.out.println(u.rdd());
        // Apply a transform to get a random double RDD following `N(1, 4)`.
        JavaDoubleRDD v = u.mapToDouble(x -> 1.0 + 2.0 * x);
        v.foreach((d)->{
            System.out.println(d);
        });
    }

    @Test
    @DisplayName("Kernel density estimation")
    public void kernelDensityEstimationTest() {
        // an RDD of sample data
        JavaRDD<Double> data = jsc.parallelize(
                Arrays.asList(1.0, 1.0, 1.0, 2.0, 3.0, 4.0, 5.0, 5.0, 6.0, 7.0, 8.0, 9.0, 9.0));

        // Construct the density estimator with the sample data
        // and a standard deviation for the Gaussian kernels
        KernelDensity kd = new KernelDensity().setSample(data).setBandwidth(3.0);

        // Find density estimates for the given values
        double[] densities = kd.estimate(new double[]{-1.0, 2.0, 5.0});

        System.out.println(Arrays.toString(densities));
    }

    @Test
    @DisplayName("Linear Support Vector Machines")
    public void SVMWithSGDTest() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("JavaSVMWithSGDExample");
        SparkContext sc = new SparkContext(conf);
        String path = BASE_RESOURCE_PATH + "/data/mllib/sample_libsvm_data.txt";
        JavaRDD<LabeledPoint> data = MLUtils.loadLibSVMFile(sc, path).toJavaRDD();

        // Split initial RDD into two... [60% training data, 40% testing data].
        JavaRDD<LabeledPoint> training = data.sample(false, 0.6, 11L);
        training.cache();
        JavaRDD<LabeledPoint> test = data.subtract(training);

        // Run training algorithm to build the model.
        int numIterations = 100;
        SVMModel model = SVMWithSGD.train(training.rdd(), numIterations);

        // Clear the default threshold.
        model.clearThreshold();

        // Compute raw scores on the test set.
        JavaRDD<Tuple2<Object, Object>> scoreAndLabels = test.map(p ->
                new Tuple2<>(model.predict(p.features()), p.label()));

        // Get evaluation metrics.
        BinaryClassificationMetrics metrics =
                new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
        double auROC = metrics.areaUnderROC();

        System.out.println("Area under ROC = " + auROC);

        // Save and load model
        model.save(sc, "target/tmp/javaSVMWithSGDModel");
        SVMModel sameModel = SVMModel.load(sc, "target/tmp/javaSVMWithSGDModel");
    }

    @Test
    public void generateKMeansRddData() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("generateKMeansRddData");
        SparkContext sc = new SparkContext(conf);
        RDD<double[]> rdd = KMeansDataGenerator.generateKMeansRDD(sc,40,5,3,1,2);
        System.out.println(rdd.count());
        rdd.take(5);
        rdd.saveAsTextFile("E://tmp//kmean");
    }

    @Test
    public void generateLinearRddData() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("generateLinearRddData");
        SparkContext sc = new SparkContext(conf);
        RDD<LabeledPoint> rdd = LinearDataGenerator.generateLinearRDD(sc,40,5,3,1,2);
        System.out.println(rdd.count());
        rdd.take(5);
        rdd.saveAsTextFile("E://tmp//linearRdd");
    }

    @Test
    public void generateLogisticRddData() {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("generateLogisticRddData");
        SparkContext sc = new SparkContext(conf);
        RDD<LabeledPoint> rdd = LogisticRegressionDataGenerator.generateLogisticRDD(sc,40,5,3,1,2);
        System.out.println(rdd.count());
        rdd.take(5);

        rdd.saveAsTextFile("E://tmp//logisticRdd");

    }

}
