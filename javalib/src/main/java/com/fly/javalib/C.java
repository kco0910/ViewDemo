package com.fly.javalib;

/**
 * Created by Fj on 2018/4/11.
 */

public class C {

    public C(){
        System.out.println("C construct");
    }
    {
        System.out.println("C construct - 2");
    }

    static {
        System.out.println("C static");
    }

    public static void main(String args[]){
        C c = new C();
    }
}
