package com.fly.javalib;

public class Test {
    public static void main(String args[]){
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operate(a,b);
        System.out.print(a+","+b);
    }

    public static void operate(StringBuffer a,StringBuffer b){
        a.append(b);
        b = a;
    }

}
