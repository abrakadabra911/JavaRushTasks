package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder result = new StringBuilder();
        if(a < b) {
            for(int i=0;i<=b-a;i++) {
                result.append(i+a);
                if(i<b-a)  result.append(" ");
            }
        }
        if(a > b) {
            for(int i=a-b;i>=0;i--) {
                result.append(i+b);
                if(i>0)  result.append(" ");
            }
        }
        result.append("\n");
        return result.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}