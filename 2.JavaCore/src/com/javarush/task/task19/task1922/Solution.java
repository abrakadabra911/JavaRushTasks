package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
       words.add("файл");
        words.add("вид");
      words.add("В");



    }

    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader filereader = new BufferedReader(new FileReader(filename));
        while (filereader.ready()) {
            String data = filereader.readLine();
            int counter1 = 0;
            int counter2 = 0;
            int counter3 = 0;
            String[] split = data.split(" ");
            for (int i = 0; i < split.length; i++) {
                if(split[i].equals(words.get(0))) counter1 =1;
                if(split[i].equals(words.get(1))) counter2 =1;
                if(split[i].equals(words.get(2))) counter3 =1;

            }
            if((counter1 + counter2 +counter3) == 2) System.out.println(data);

        }


        filereader.close();
    }
}
