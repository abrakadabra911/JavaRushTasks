package com.javarush.task.task39.task3908;

import java.util.ArrayList;
import java.util.List;

/*
Возможен ли палиндром?
Реализуй метод isPalindromePermutation(String s) который будет возвращать true, если из всех символов строки s можно составить палиндром. Иначе — false.

Символы в анализируемой строке ограничены кодировкой ASCII.
Регистр букв не учитывается.


Требования:
1. Метод isPalindromePermutation должен возвращать true, если выполнив перестановку символов входящей строки можно получить палиндром.
2. Метод isPalindromePermutation должен возвращать false, если выполнив перестановку символов входящей строки получить палиндром невозможно.
3. Метод isPalindromePermutation должен быть публичным.
4. Метод isPalindromePermutation должен быть статическим.
*/
public class Solution {
    public static void main(String[] args) {

        System.out.println(isPalindromePermutation( "aa&"));

    }

    public static boolean isPalindromePermutation(String s) {

        if(!s.matches("\\A\\p{ASCII}*\\z")) return false;
        if (s.length() == 0) {
            return true;
        }

        // Lower case the String and remove white spaces
        s = s.replaceAll(" ", "");

        String caseExcluded = s.toLowerCase();

        char[]chars = caseExcluded.toCharArray();
        List<Character> list = new ArrayList<>();
        for(char x: chars) list.add(x);

       int repeat = list.size();
       int oddCounter = 0;

        for(int i = 0; i < repeat; i++) {
            Character target = list.get(0);
            int charCounter = 0;

            for(int j = 0; j < list.size(); j++) {
                if(target.equals(list.get(j))) {
                    list.remove(j);
                    j--;
                    charCounter ++;
                }
            }
          //  list.remove(0);

            if( charCounter%2 == 1) oddCounter++;
            if(list.size()==0) break;
          /*  if (oddCounter > 1) {
                System.out.println(oddCounter);
                return false;
            }*/
        }
        // System.out.println(oddCounter);
        return true;
    }
}
