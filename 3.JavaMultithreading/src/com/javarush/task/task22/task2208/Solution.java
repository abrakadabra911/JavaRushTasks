package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        map.put("gorod", "Baranovichi");
        map.put("имя", "Вася");
        map.put("пол", null);

        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
           // System.out.println(pair.getKey() + " = " + pair.getValue());
            if (pair.getValue()!= null) {
                if (!flag) {
                    builder.append(pair.getKey()+ " = " + "\'" + pair.getValue() + "\'");
                    flag = true;
                }
                else {builder.append(" and " + pair.getKey()+ " = " + "\'" + pair.getValue() + "\'");}
            }
        }
        return builder.toString();
    }
}
