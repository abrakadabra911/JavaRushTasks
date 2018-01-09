package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {

    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer strt = new StringTokenizer(query, delimiter);
        ArrayList<String> list = new ArrayList<>();
        while (strt.hasMoreTokens()) {
            list.add(strt.nextToken());
        }
        String[] result = new String[list.size()];
        for(int i = 0; i < list.size(); i++) result[i] = list.get(i);

        return result;
    }
}
