package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args)throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName2));
        String input;
        String[] splited;
        while ((input = fileReader.readLine()) != null) {
            splited = input.split(" ");
            for (int i = 0; i < splited.length; i++) {
                if (splited[i].matches(".*\\d+.*"))  fileWriter.write(splited[i] + " ");

            }
        }
            fileReader.close();
             fileWriter.close();
    }
}
