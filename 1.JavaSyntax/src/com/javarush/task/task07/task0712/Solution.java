package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int min = 2000000000;
        int max = 0;
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
           String x = reader.readLine();
            lines.add(x);
            if(x.length() < min) min = x.length();
            if(x.length() > max) max = x.length();
        }

        for (String x: lines) {
            if (x.length() == min) {
                System.out.println(x);
                break;
            }
            if (x.length() == max) {
                System.out.println(x);
                break;
            }

        }



    }
}
