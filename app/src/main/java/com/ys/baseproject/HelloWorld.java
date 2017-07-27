package com.ys.baseproject;

/**
 * Created by yunshan on 17/5/25.
 */

public class HelloWorld {

    public static native String sayHello(String name);

    public static void main(String[] args) {

        String text = sayHello("Hello JNI");
        System.out.println(text);

    }

    static{
        System.loadLibrary("HelloWorld");
    }

}
