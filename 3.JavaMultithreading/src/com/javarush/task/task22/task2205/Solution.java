package com.javarush.task.task22.task2205;

import java.text.MessageFormat;

/*
МНЕ нравится курс JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(String.format(getFormattedString(), "JavaRush", "курс", "мне", "нравится"));
        //должен быть вывод
        //"МНЕ нравится курс JavaRush"
    }
    public static String getFormattedString() {

        return "%3$S %4$s %2$s %1$s";
    }
}
