package com.frank;

/**
 * 这个版本的代码看起来有点复杂，注意其中有两次if (instance == null)的判断，这个叫做『双重检查 Double-Check』。
 * 第一个if (instance == null)，其实是为了解决Version2中的效率问题，只有instance为null的时候，才进入synchronized的代码段——大大减少了几率。
 * 第二个if (instance == null)，则是跟Version2一样，是为了防止可能出现多个实例的情况
 * Created by Aaron.qiu on 2017/4/22.
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2() {}
    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
