package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by leha on 2017-04-13.
 */
public class Solution {
    public static void main (String args[]) {
        Solution solution = new Solution();
       solution.testStrategy(new HashMapStorageStrategy(), 100);
         solution.testStrategy(new OurHashMapStorageStrategy(), 100);
        solution.testStrategy(new FileStorageStrategy(), 100);
     /*   FileBucket bucket = new FileBucket();
        Entry entry = new Entry(12345, 222L, "znahenije", null);
        System.out.println(entry.getKey() + " " + entry.getValue());
        bucket.putEntry(entry);
        entry = bucket.getEntry();
        System.out.println(entry.getKey() + " " + entry.getValue());*/

    }
   public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set set = new HashSet();
        Iterator it = strings.iterator();
        while (it.hasNext()) {
            String string = (String)it.next();
            set.add(shortener.getId(string));
        }
        return set;
    }
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set set = new HashSet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            Long lonng = (Long)it.next();
            set.add(shortener.getString(lonng));
        }
        return set;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSetStrings  = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            testSetStrings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);
        Date date1 = new Date();
        Set<Long> keys = getIds(shortener,testSetStrings );
        Date date2 = new Date();
        Long vremia = date2.getTime()-date1.getTime();
        Helper.printMessage(vremia.toString());

        Date date3 = new Date();
        Set<String> strings2 = getStrings(shortener, keys);
        Date date4 = new Date();
        Long vremia2 = date4.getTime()-date3.getTime();
        Helper.printMessage(vremia2.toString());

        Helper.printMessage(testSetStrings.equals(strings2)? "Тест пройден.":"Тест не пройден.");


    }
}
