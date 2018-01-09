package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws ParseException{
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm:ss");

        Date date1 = df.parse("30.08.2012 16:08:40");
        Date date2 = df.parse("29.2.2028 5:4:7");

        Map<Integer, Integer> map = new HashMap<>();
        map = logParser.getAllDoneTasksAndTheirNumber(date1,date2);
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }


    }
}