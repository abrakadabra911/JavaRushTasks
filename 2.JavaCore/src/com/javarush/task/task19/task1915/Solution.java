package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

import static java.lang.Character.isDigit;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedOutputStream filewriter= new BufferedOutputStream(new FileOutputStream(filename));


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
        byte[] result2 = result.getBytes();

        filewriter.write(result2);

        //Возвращаем все как было
        System.setOut(consoleStream);
        filewriter.close();
        System.out.print(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

