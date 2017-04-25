package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<K, V>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        V value = cache.get(key);
if (value ==null) {
    value=clazz.getConstructor(key.getClass()).newInstance(key);
    cache.put(key, value);
}
        return value;
    }

    public boolean put(V obj) {
        Method method = null;
        K key = null;
        try {
            Class claz = obj.getClass();
            method = claz.getDeclaredMethod("getKey");
            method.setAccessible(true);
            key = (K)method.invoke(obj);
            cache.put(key, obj);
        } catch (Exception e) {
            return false;

        }
            return true;
    }

    public int size() {
        return cache.size();
    }

}
