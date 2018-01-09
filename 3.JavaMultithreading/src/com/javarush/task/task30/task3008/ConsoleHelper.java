package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by leha on 2017-06-18.
 */
public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String result = "";
        try {
             result = reader.readLine();
            while (reader.ready()) result+=reader.readLine();
        }
        catch (IOException e) {System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            result = readString();
        }
        return result;
    }

   public static int readInt() {
        int result = 0;
        try{
           result = Integer.parseInt(readString());
        }
        catch (NumberFormatException e) {
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            result = readInt();
        }
            return result;
    }


}
