package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
static {
    map.put(0, "nul");
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");
    map.put(4, "four");
    map.put(5, "five");
    map.put(6, "six");
    map.put(7, "seven");
    map.put(8, "eight");
    map.put(9, "nine");
    map.put(10, "ten");
    map.put(11, "eleven");
    map.put(12, "twelve");
}
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader filereader = new BufferedReader(new FileReader(filename));
        while (filereader.ready()) {
            String data = filereader.readLine();
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                data = data.replaceAll("(?i)\\b" + entry.getKey() + "\\b", entry.getValue());
            }
            System.out.println(data);
        }
        filereader.close();
    }
}
