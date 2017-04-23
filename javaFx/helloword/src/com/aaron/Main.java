package com.aaron;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * http://www.oracle.com/technetwork/java/javase/archive-139210.html
 * http://docs.oracle.com/javafx/scenebuilder/1/use_java_ides/sb-with-intellij.htm#CEGJBHHA
 * http://www.javafxchina.net/blog/2016/11/javafx-scene-builder/
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(Thread.currentThread().getContextClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
