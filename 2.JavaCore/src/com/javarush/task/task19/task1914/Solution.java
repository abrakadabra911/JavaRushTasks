package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);
        //Вызываем функцию, которая ничего не знает о наших манипуляциях
        testString.printSomething();

        //Преобразовываем записанные в наш ByteArray данные в строку
        String result = outputStream.toString();
        //Возвращаем все как было
        String[] split = result.split(" ");
        int a = 0;
        int b = 0;
        if (Integer.parseInt(split[0]) > 0) a = Integer.parseInt(split[0]);
        if (Integer.parseInt(split[2]) > 0) b = Integer.parseInt(split[2]);

        String result2 = "";
        if (split[1].equals("+")) result2 = result + (a+b);
        if (split[1].equals("-")) result2 = result + (a-b>0? a-b:0);
        if (split[1].equals("*")) result2 = result + (a*b);


        System.setOut(consoleStream);
        System.out.print(result2);
    }

    public static class TestString {
        public void printSomething() {
            System.out.print("3 + 6 = ");
        }
    }
}

