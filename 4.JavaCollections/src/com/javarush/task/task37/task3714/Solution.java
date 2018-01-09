package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }
    /*
           1	I
           5	V
           10	X
           50	L
           100	C
           500	D
           1000 M
           */
    public static int romanToInteger(String s) {
        Map map = new TreeMap();
        map.put( 'I', 1);
        map.put( 'V', 5);
        map.put( 'X', 10);
        map.put( 'L', 50);
        map.put( 'C',100);
        map.put( 'D', 500);
        map.put( 'M', 1000);

        int result = 0;
        int prev = 0;
        char[] chars = s.toCharArray();
        List <Integer> previous = new ArrayList();

        for (int i = 0; i < chars.length; i++) {
            int number = (int)map.get(chars[i]);

            if(i == 0) {
                previous.add(number);
                prev = number;
            }

            else {

                if(number > prev) {
                    for(Integer x: previous) {
                        result -= x;
                    }
                    previous.clear();
                }

                if(number < prev) {
                    for(Integer x: previous) {
                        result += x;
                    }
                    previous.clear();
                }

                previous.add(number);

                if (i == chars.length-1) {
                    for(Integer x: previous) {
                        result += x;
                    }
                }
            }



        }


        return result;
    }
}
