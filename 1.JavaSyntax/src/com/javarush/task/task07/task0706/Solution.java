package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
       int [] doma = new int[15];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int chetnyje = 0;
        int ne_chetnyje = 0;
        for (int i =0; i < doma.length; i++) {
            doma[i] = Integer.parseInt(reader.readLine());
            if (i == 0 || i%2 ==0) chetnyje += doma[i];
           else ne_chetnyje += doma[i];
        }
        if (ne_chetnyje > chetnyje) System.out.println("В домах с нечетными номерами проживает больше жителей.");
        if (chetnyje > ne_chetnyje) System.out.println("В домах с четными номерами проживает больше жителей.");

    }
}
