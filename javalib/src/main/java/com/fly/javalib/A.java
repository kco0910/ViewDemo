package com.fly.javalib;

/**
 * Created by Fj on 2018/4/11.
 */

public class A {
    public static void main(String args[]){
        try {
            Class.forName("com.fly.javalib.B");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
