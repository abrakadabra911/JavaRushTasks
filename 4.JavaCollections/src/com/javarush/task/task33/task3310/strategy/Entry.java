package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;


/**
 * Created by leha on 2017-04-15.
 */
public class Entry implements Serializable{
   public Entry(int hash, Long key, String value, Entry next) {
        this.hash = hash;
        this.key = key;
        this.value= value;
        this.next = next;
    }

    Long key;

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    String value;
     Entry next;
     int hash;

   public Long getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (!key.equals(entry.key)) return false;
        return value.equals(entry.value);

    }

    public String toString() {
        String a = key + "=" + value;
        return a;
    }


}
