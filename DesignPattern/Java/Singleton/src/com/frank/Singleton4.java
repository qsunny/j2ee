package com.frank;

/**
 * http://mp.weixin.qq.com/s/5G6FAgTMDT0BgxP323FAYg
 * 什么时候是类装载时
 * 1. new一个对象时
 * 2. 使用反射创建它的实例时
 * 3. 子类被加载时，如果父类还没被加载，就先加载父类
 * 4. jvm启动时执行的主类会首先被加载
 * 《Effective Java》一书的第一版中推荐了一个中写法
 * 这种写法非常巧妙：
 * 对于内部类SingletonHolder，它是一个饿汉式的单例实现，在SingletonHolder初始化的时候会由ClassLoader来保证同步，使INSTANCE是一个真·单例。
 * 同时，由于SingletonHolder是一个内部类，只在外部类的Singleton的getInstance()中被使用，所以它被加载的时机也就是在getInstance()方法第一次被调用的时候。
 * Created by Aaron.Qiu on 2017/4/22.
 */
public class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4 (){}
    public static final Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
