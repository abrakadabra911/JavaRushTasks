package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/


import java.math.BigDecimal;
import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        try{
        if(args[0].length() > 255) {
            System.out.println("incorrect");
            return;
        }
        char[] chars  = args[0].toUpperCase().toCharArray();
        for(char x: chars) {
            if( x > 90 || (x < 65 && x > 57) || x < 48){
                System.out.println("incorrect");
                return;
            }
        }
        int result = -1;


        for (int i = 2; i < 37; i++) {
            BigDecimal dec = null;

            try {
                dec = new BigDecimal(new BigInteger(args[0].toUpperCase(), i));
                result = i;
                break;
            }
            catch (Exception e) {}


        }

        if(result == -1){
            System.out.println("incorrect");
        }
        else {
            System.out.println(result);
        }
    }
            catch (Exception e) {}
    }

}