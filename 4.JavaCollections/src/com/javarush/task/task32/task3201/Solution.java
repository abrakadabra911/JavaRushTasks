package com.javarush.task.task32.task3201;

import java.io.*;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException{
        int position = Integer.parseInt(args[1]);
        String text = args[2];
        byte b[] = text.getBytes();
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");



        if(position>raf.length()) position = (int)raf.length();
     //   if (a.length < (b.length+position))raf.seek(a.length - b.length);
        raf.seek(position);

//пишем в файл строку, начиная с 0-го байта
        raf.write(b);

//закрываем файл
        raf.close();
    }
}
