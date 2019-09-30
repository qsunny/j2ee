package com.aaron.spark;

import com.aaron.spark.service.ISparkService;
import com.aaron.spark.service.impl.*;

public class DemoApp {


    public static void main(String[] args) {
//    ISparkService sparkFileService = new SimpleFileServiceImpl();
//    ISparkService sparkFileService = new SimpleFileServiceImpl();
//    ISparkService sparkFileService = new SimpleRDDServiceImpl();
//    ISparkService sparkFileService = new FeatureExtractingServiceImpl();
//    ISparkService sparkFileService = new FeatureHasherServiceImpl();
    ISparkService sparkFileService = new FeatureExtracting4EncapsulationServiceImpl();
//    ISparkService sparkFileService = new DecisionTreeRegressionServiceImpl();
//    ISparkService sparkFileService = new MultilayerPerceptronClassifierServiceImpl();
//    ISparkService sparkFileService = new JavaModelSelectionViaCrossValidationServiceImpl();
//    ISparkService sparkFileService = new JavaModelSelectionViaTrainValidationSplitServiceImpl();
        System.setProperty("hadoop.home.dir","E:\\soft\\hadoop-3.2.0\\" );
        sparkFileService.laodFile();


    }
}
