package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;


/*
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();
        int n = 1;
        int max = 0;
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }
        for (int i = 1; i<list.size(); i++) {
            if (list.get(i) == list.get(i-1)) {
                n++;
                if (i == (list.size()-1)&& max<n) {
                    max = n;
                    break;
                }
            }
            else if (max < n) {
                max = n;
                n = 1;
            }
            else n = 1;
        }
        System.out.println(max);
    }
}