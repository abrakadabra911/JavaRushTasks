package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
       int size = 0;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
           List<V> list = entry.getValue();
            for (V v : list) {
                size++;
            }
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        V result = null;
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) {
                List<V> list = entry.getValue();
                int length = list.size();
                result = list.get(length-1);
                if (length < repeatCount) {
                    list.add(value);
                    map.put(entry.getKey(), list);
                }
                else {
                    list.remove(0);
                  list.add(value);
                }
            return result;
            }
        }
        List list2 = new ArrayList<V>();
        list2.add(value);
        map.put(key, list2);
        return result;
    }

    @Override
    public V remove(Object key) {

        V result = null;
        if(!map.containsKey(key)){
            return result;
        }
        List<V> list = map.get(key);
        if (list.size() > 0) {
            result = list.get(0) ;
            list.remove(0);
            map.put((K)key, list);
            if (list.size()==0) {
                map.remove(key);
            }
        }

        return result;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            set.add(entry.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
       List list = new ArrayList();
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            for(V v: entry.getValue()) {
                list.add(v);
            }
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
       return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
                if (entry.getValue().contains(value)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}