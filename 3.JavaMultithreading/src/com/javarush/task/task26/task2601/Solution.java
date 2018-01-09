package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
     /*   Integer[] aray = {3, 4, 7, -1, 6, 2, 1, -18, 11, 5,10, 48, 49, -23, 92, 94};
        Integer[] aray2 = sort(aray);
        for(int i = 0; i < aray2.length; i++){
        System.out.println(aray2[i]);}*/
    }

    public static Integer[] sort(Integer[] array) {
        if(array == null) return null;

        Comparator<Integer> compare_low_high = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        List not_sorted_list = new ArrayList();
        for(int i = 0; i < array.length; i++) not_sorted_list.add(array[i]);
        Collections.sort(not_sorted_list, compare_low_high);


            System.out.println(not_sorted_list);

        final double mediana;
        if (array.length % 2 == 0)
           mediana = (Double.valueOf(not_sorted_list.get(array.length/2-1).toString())+Double.valueOf(not_sorted_list.get(array.length/2).toString()))/2;
        else
            mediana = (int)not_sorted_list.get(array.length/2);
     //   System.out.println("медиана = " +mediana);

        Comparator<Integer> compareByMediana = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                double a = Math.abs(o1-mediana);
                double b = Math.abs(o2-mediana);
                if(a-b!=0) return (int)(Math.abs(o1-mediana) - Math.abs(o2-mediana));
                return o1-o2;
            }
        };
        Collections.sort(not_sorted_list, compareByMediana);
        Integer[] result = new Integer[not_sorted_list.size()];
        for(int i = 0; i < array.length; i++) result[i] = (Integer)not_sorted_list.get(i);
        return result;
    }
}
