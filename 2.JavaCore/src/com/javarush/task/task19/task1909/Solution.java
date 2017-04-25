package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        BufferedReader instr = new BufferedReader(new FileReader(filename1));
        BufferedWriter outstr = new BufferedWriter(new FileWriter(filename2));

        while(instr.ready() ){
            String data = instr.readLine();
            String data2 = data.replaceAll("\\.", "!");
            outstr.write(data2);
            outstr.newLine();
        }

        instr.close();
        outstr.close();
    }

}
