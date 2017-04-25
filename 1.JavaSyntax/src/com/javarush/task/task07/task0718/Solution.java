package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int max = 0;
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String x = reader.readLine();
            lines.add(x);
        }
        for (int i = 0; i < 10; i++) {
            if(lines.get(i).length() >= max) max = lines.get(i).length();
            else System.out.println(i);
        }

    }
}

