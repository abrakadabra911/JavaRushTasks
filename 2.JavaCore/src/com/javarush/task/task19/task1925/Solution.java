package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        BufferedReader filereader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter filewriter = new BufferedWriter(new FileWriter(fileName2));
        String[] split;
        String bufer;
        boolean first = true;
        while (filereader.ready()) {
            String data = filereader.readLine();
            split = data.split(" ");
            for (int i = 0; i < split.length; i++) {
                if (split[i].length() > 6) {
                    if (!first) {
                        filewriter.write("," + split[i]);
                    }
                    if (first) {
                        filewriter.write(split[i]);
                        first = false;
                    }
                }
            }
        }


            filereader.close();
            filewriter.close();
        }
    }
