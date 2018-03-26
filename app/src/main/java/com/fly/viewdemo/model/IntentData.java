package com.fly.viewdemo.model;

/**
 * Created by Fj on 2018/3/22.
 */

public class IntentData {
    public  String key;
    public  Class cls;
    public IntentData(String key,Class cls){
        this.key = key;
        this.cls  = cls;
    }

    @Override
    public String toString() {
        return key;
    }
}
