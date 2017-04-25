package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        BufferedReader filereader = new BufferedReader(new FileReader(filename));
        String data = "";
        String s;
while (filereader.ready()) {
    s = filereader.readLine();
    data = data + s;
}
        filereader.close();
        ArrayList<int[]> list = new ArrayList<>();


        Pattern p1 = Pattern.compile("<" + args[0] + "[ >]");
        Pattern p2 = Pattern.compile("</" + args[0] + ">");
        Matcher m1 = p1.matcher(data);
        while (m1.find()) {
            int[] q = new int[2];
            q[0] = m1.start();
            q[1] = 1;
            list.add(q);
        }
        Matcher m2 = p2.matcher(data);
        while (m2.find()) {
            int[] q = new int[2];
            q[0] = m2.end()-1;
            q[1] = 0;
            list.add(q);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size()-1 ; j++) {
                if (list.get(j)[0] > list.get(j+1)[0]) {
                    int[] temp = list.get(j);
                    list.remove(j);
                    list.add(j+1, temp );
                }
            }
        }

for (int i = 0; i < list.size(); i++) {
int open = 0;
    if(list.get(i)[1]==1) {
        open++;
        int j = i;
        while (open != 0) {
            j++;
            if (list.get(j)[1] == 1) open++;
            if (list.get(j)[1] == 0) open--;
        }
        System.out.println(data.substring(list.get(i)[0], list.get(j)[0] + 1));
    }
}

    }
}
