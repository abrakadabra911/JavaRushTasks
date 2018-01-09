package com.javarush.task.task22.task2203;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        char chars[] = string.toCharArray();
        int counter=0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\t')  counter++;
        }
        if (counter<2) throw new TooShortStringException();
        String pattern = "\\t[^\\t]+\\t";
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(string);

        m.find();
        String result = m.group(0);

        return result.substring(1,result.length()-1);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        try {
            System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
        }
        catch (TooShortStringException e) {}
    }
}
