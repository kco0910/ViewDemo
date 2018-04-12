package com.fly.javalib;

/**
 * Created by Fj on 2018/4/11.
 */

public class B {
    public static B t1 = new B();
    public static B t2 = new B();
    public B(){//构造函数
        System.out.println("construct real");
    }
    { //构造代码块
        System.out.println("construct 1");
    }

    static {
        System.out.println("static");
    }

    public static void main(String[] args) {
        B t = new B();
    }
}
