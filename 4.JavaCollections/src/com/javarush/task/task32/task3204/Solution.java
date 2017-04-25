package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] aLPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] figures = "1234567890".toCharArray();
        int counter = 0;
        while(true) {
            bos.write(alphabet[(int)(Math.random()*(alphabet.length-1))]);
            counter++;
            if (counter == 8) break;
            bos.write(aLPHABET[(int)(Math.random()*(aLPHABET.length-1))]);
            counter++;
            if (counter == 8) break;
            bos.write(figures[(int)(Math.random()*(figures.length-1))]);
            counter++;
            if (counter == 8) break;
        }




        return bos;
    }
}