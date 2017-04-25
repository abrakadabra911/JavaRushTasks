package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
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
        String promo = "JavaRush — курсы Java онлайн";
StringBuilder result_finish = new StringBuilder();

        int counter = 0;
        for (int i = 0; i <result.split("\n").length; i++ ) {
            String s = result.split("\n")[i];
            if(i == result.split("\n").length-1) {
                result_finish.append(s);
                counter++;
                if (counter % 2 == 0) {
                    result_finish.append("\n"+ promo);
                }
            }
            else {
                result_finish.append(s + "\n");
                counter++;
                if (counter % 2 == 0) {
                    result_finish.append( promo + "\n");
                }
            }
        }
        String result_finish1 = result_finish.toString();
        result_finish1.trim();
        //Возвращаем все как было
        System.setOut(consoleStream);
        System.out.println(result_finish1);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
