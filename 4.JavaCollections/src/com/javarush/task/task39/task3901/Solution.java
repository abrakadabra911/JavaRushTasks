package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if(s == null || s.equals("")) return 0;
        int max = 1;

        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length-1; i++) {
            Set<Character> set = new HashSet<>();
            set.add(chars[i]);
            int sizeOfSet = set.size();

            for(int j = i+1; j < chars.length; j++) {
                set.add(chars[j]);
                if(set.size() > sizeOfSet) max = set.size()>max? set.size() : max;
                else{break;}

                sizeOfSet = set.size();
            }
        }
        return max;
    }
}
