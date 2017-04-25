package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/


import java.io.*;


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename1 = reader.readLine();
        String filename2 = reader.readLine();
        reader.close();
        BufferedReader instr = new BufferedReader(new FileReader(filename1));
        String s = "";
while(instr.ready() ){
    String data = instr.readLine();
    String[] data_split = data.split(" ");
    for(String x: data_split) {
        if (isNumeric(x)) {
            s += x;
            s += " ";
        }
    }
}

        instr.close();
        BufferedWriter outstr = new BufferedWriter(new FileWriter(filename2));
        outstr.write(s);
        outstr.close();
    }
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
