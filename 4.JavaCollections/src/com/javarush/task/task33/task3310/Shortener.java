package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;


/**
 * Created by leha on 2017-04-13.
 */
public class Shortener {
   private Long lastId = 0L;
    private StorageStrategy storageStrategy;
    public Shortener(StorageStrategy str) {
        storageStrategy = str;
    }

    synchronized public Long getId(String string){
        if (storageStrategy.containsValue(string)) return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }
   synchronized public String getString(Long id) {
        return storageStrategy.getValue(id);
    }




}
