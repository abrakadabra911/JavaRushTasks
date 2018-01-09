package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)) ;
        FileReader freader = new FileReader(new File(reader.readLine()));
        StringBuilder buffer = new StringBuilder();
        while(freader.ready()) {
            char a = (char)freader.read();
            buffer.append(a);
        }
        reader.close();
        freader.close();
        String bufferstr = buffer.toString();
        String[] list2 = bufferstr.split("( )|(  )|\\n|\\r");

        List<String> temp = new ArrayList();
        for (String x: list2) {
            if (x!=null && !x.equals("")) {
                temp.add(x);
            }
        }
        String [] list = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            list[i] = temp.get(i);
        }
        for (int i =0 ; i < list.length-1; i++) {
                        StringBuilder strb = new StringBuilder(list[i]);
                        String reverse = strb.reverse().toString();
                        for (int j =i+1 ; j < list.length; j++) {
                            if (!reverse.equals("") && reverse.equals(list[j])) {
                                Pair pair = new Pair();
                                pair.first = strb.reverse().toString();
                                pair.second = list[j];
                                list[j] = "";
                                result.add(pair);

                                break;
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
