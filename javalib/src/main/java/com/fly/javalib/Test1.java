package com.fly.javalib;

import java.util.Scanner;

/**
 * Created by Fj on 2018/4/12.
 */

public class Test1 {
    public int num = 9;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String value;
        int num;
        Test1 text1  = new Test1();
        do {
            System.out.println("please input");
            value = input.next();
            try {
                num = Integer.parseInt(value);
            } catch (NumberFormatException e) {
               num = 0;
            }
            text1.num = num;
            text1.fun();
        }while (!value.equals("#"));
        input.close();
    }

    public void fun(){
        String newValueStr = String.valueOf(num-1);
        String preValue = String.valueOf(num);
        int count = Math.min(newValueStr.length(),preValue.length());
        System.out.println("newValueStr:"+newValueStr+",preValue:"+preValue);
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (preValue.charAt(i)!=newValueStr.charAt(i)){
                index = i;
                break;
            }
        }
        if (index != -1){
            String newSub = newValueStr.substring(index);
            String preSub = preValue.substring(index);
            System.out.println("newSub:"+newSub+",preSub:"+preSub);
        }
    }
}

