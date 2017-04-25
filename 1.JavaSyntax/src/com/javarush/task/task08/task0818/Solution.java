package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        Map<String, Integer> list = new HashMap<>();
        list.put ("ivanov", 1500);
        list.put ("ivanov2", 2500);
        list.put ("ivanov3", 3500);
        list.put ("ivan3ov4", 4500);
        list.put ("ivanov5", 5500);
        list.put ("ivanov6", 500);
        list.put ("ivanov7", 300);
        list.put ("ivanov8", 200);
        list.put ("ivanov9", 100);
        list.put ("ivanov10", 0);
        return (HashMap)list;
    }

    public static void removeItemFromMap(HashMap<String, Integer> copy) {
        Iterator<Map.Entry<String, Integer>> iterator = copy.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            if (pair.getValue() < 500) {
                iterator.remove();
            }
        }
    }


    public static void main(String[] args) {


    }
}