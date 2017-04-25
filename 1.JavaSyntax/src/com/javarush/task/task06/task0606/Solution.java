package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char chisla[] = reader.readLine().toCharArray();
        for (char x: chisla) {
            int m = Character.getNumericValue(x);
            if (m%2==0) even++;
            else {odd++;}
        }

        System.out.println("Even: " + even +" Odd: " +odd);
    }
}
