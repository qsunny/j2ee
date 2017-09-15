package com.aaron.java8example;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;

public class LambdaExample {

    Runnable r1 = () -> { System.out.println(this); };
    Runnable r2 = () -> { System.out.println(toString()); };

    public void lambadSyntax() {
        FileFilter java = (File f) -> f.getName().endsWith("*.java");

        //String user = doPrivileged(() -> System.getProperty("user.name"));
        new Thread(() -> {
            //connectToService();
            //sendNotification();
            System.out.println("thread start");
        }).start();

        Comparator<String> c = (s1, s2) -> s1.compareToIgnoreCase(s2);
        int result = c.compare("2","5");

    }
}
