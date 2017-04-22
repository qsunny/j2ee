package com.frank;

/**
 * 解决 原子操作 指令重排
 * Created by Aaron.Qiu on 2017/4/22.
 */
public class Singleton3 {
    private static volatile Singleton3 instance;
    private Singleton3() {}
    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
