package com.aaron.spark;

import com.aaron.spark.service.ISparkService;
import com.aaron.spark.service.impl.SimpleFileServiceImpl;
import com.aaron.spark.service.impl.SimpleRDDServiceImpl;

public class DemoApp {


    public static void main(String[] args) {
//    ISparkService sparkFileService = new SimpleFileServiceImpl();
//    ISparkService sparkFileService = new SimpleFileServiceImpl();
    ISparkService sparkFileService = new SimpleRDDServiceImpl();
        System.setProperty("hadoop.home.dir","E:\\soft\\hadoop-3.2.0\\" );
        sparkFileService.laodFile();


    }
}
