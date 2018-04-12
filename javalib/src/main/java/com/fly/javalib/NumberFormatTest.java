package com.fly.javalib;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberFormatTest {
    public static void main(String args[]){
        float value = 123456789128f;
//        System.out.println("method --- 1:"+big(value));
//        System.out.println("method  --- 2:"+big2(value));
        System.out.println("method  --- 3:"+big3(String.valueOf(value)));
    }

    private static String big(double d) {
        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }

    private static String big2(double d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString();
    }

    private static String big3(String value){
        BigDecimal num = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat("0");
        String res = df.format(num);
        return res;
    }

}
