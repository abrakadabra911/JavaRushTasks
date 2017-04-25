package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int line;
        int result = 0;
        int counter = 0;
        while((line = Integer.parseInt(reader.readLine())) != -1) {
            result+=line;
            counter++;
        }
        double d = (double)((double)result/(double)counter);
       System.out.println(d);

    }
}

