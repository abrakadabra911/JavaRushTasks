package com.javarush.task.task22.task2202;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        char chars[] = string.toCharArray();
        int counter=0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 040)  counter++;
        }
        if (counter<4) throw new TooShortStringException();

        String pattern = "\\s(\\S)+\\s(\\S)+\\s(\\S)+\\s(\\S)+";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(string);

        m.find();
        String result = m.group(0);

        return result.substring(1,result.length());

    }
    public static class TooShortStringException extends RuntimeException{

    }
}
