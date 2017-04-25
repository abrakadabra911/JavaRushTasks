package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics
*/
public class Solution <T,K,V>{

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        ArrayList <T> arrayList = new ArrayList<>();
        for(T obj : elements) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static<T> HashSet<T> newHashSet(T... elements) {
        HashSet <T> arrayList = new HashSet<>();
        for(T obj : elements) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static<K,V>  HashMap<K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        HashMap<K,V> hashmap = new HashMap<>();
        if (keys.size() != values.size()) throw new IllegalArgumentException("ошибка") ;
        for (int i = 0; i < keys.size(); i++) {
            hashmap.put(keys.get(i), values.get(i));
        }
        return hashmap;
    }
}
