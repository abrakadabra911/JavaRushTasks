package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
   public static String convertByteToString(byte readBytes[]) {
       return new String(readBytes);
   }
    public static void main(String... args)throws IOException {
        int position = Integer.parseInt(args[1]);
        String text = args[2];
        byte b[] = text.getBytes();
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        raf.seek(position);

        byte reader[] = new byte[b.length];

        raf.read(reader, 0, text.length());
        raf.seek(raf.length());


        if (Solution.convertByteToString(reader).equals(text)) { raf.write("true".getBytes());}
       else {raf.write("false".getBytes());}

//закрываем файл
        raf.close();
    }
}

