package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by leha on 2017-04-14.
 */
public class HashMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> data;
    public HashMapStorageStrategy () {
        data = new HashMap<>();
    }
    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key,value);
    }

    @Override
    public Long getKey(String value) {
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getValue().equals(value)) return (Long)pair.getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
