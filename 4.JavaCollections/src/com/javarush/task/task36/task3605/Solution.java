package com.javarush.task.task36.task3605;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet set = new TreeSet();
        File file = new File (args[0]);
        FileReader instr = new FileReader(file);
        while (instr.ready()) {
            char c = Character.toLowerCase((char)instr.read());
            set.add(c);
        }
        int counter = 0;
for(Object c: set) {
    if((char)c >= 97 && (char)c <= 122) {
        System.out.print(c);
        counter++;
    }
    if (counter == 5) break;
}

    }
}
