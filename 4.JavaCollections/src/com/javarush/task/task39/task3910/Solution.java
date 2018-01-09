package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        int input = 2;

        System.out.println(isPowerOfThree(input));

        System.out.println((double)input/3);
    }

    public static boolean isPowerOfThree(int n) {
        if(n==0) return false;


        while ((n % 3) == 0) {
            n = n / 3;
        }

        if (n == 1)
            return true;

        return false;
    }
}
