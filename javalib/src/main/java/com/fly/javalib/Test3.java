package com.fly.javalib;

import java.math.BigDecimal;

/**
 * Created by Fj on 2018/4/9.
 */

public class Test3 {

    public static void main(String args[]){
        float num = 22.6f;
        String str = formatFloat(num);
        System.out.println("xxxx :"+str);
    }


    public static synchronized String formatFloat(float num) {
        //检测是否为非法数值
        if (Float.isInfinite(num) || Float.isNaN(num)) {
            return "NaN";
        }
        BigDecimal bigDecimal = new BigDecimal(num);
        String numStr = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        /*
        * 下面的转型逻辑是：
        * 1，可转成整数的直接转型成整数的String类型输出，例如 5.0 返回 "5"
        * 2，如果有小数点的，判断是否能否尽可能保留一位小数。 例如 5.5 返回 "5"
        * 3，最多保留两位小数，两位后面的小数不管多大，统统无视掉。 例如 3.1415926 返回 "3.14"
        * PS：这是上级的指示！！！请同事们务必遵循此逻辑，未经上级批准切勿修改，因为直接影响屏幕展示！
        * PS：这是上级的指示！！！请同事们务必遵循此逻辑，未经上级批准切勿修改，因为直接影响屏幕展示！
        * PS：这是上级的指示！！！请同事们务必遵循此逻辑，未经上级批准切勿修改，因为直接影响屏幕展示！
        *                                                                       write by liaohailong
        * */
        String[] split = numStr.split("\\.");
        String decimal = split[1];
        String secondDecimal = decimal.substring(1, 2);
        //检查是否需要保留1位小数
        if ("0".equals(secondDecimal)) {
            String firstDecimal = decimal.substring(0, 1);
            //检查是否不要保留小数
            if ("0".equals(firstDecimal)) {
                return bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            }
            return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).toString();
        }
        //此处返回两位小数
        return numStr;
    }
}
