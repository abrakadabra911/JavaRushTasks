package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {


        String result2 = "";
        try {
            BufferedReader br = new BufferedReader(reader);
            if (br != null) {
                ArrayList<Character> list = new ArrayList<>();
                String result = "";
                while (br.ready()) {
                    String line = br.readLine();
                    if (line == null) break;
                    result += line;
                }

                char[] chars = result.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    chars[i] = (char) (chars[i] + key);
                }
                result2 = String.valueOf(chars);
                return result2;
            }
        }
    catch (Exception e) {return "";}
        return "";
    }
}
