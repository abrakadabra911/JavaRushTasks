package com.javarush.task.task33.task3310.strategy;

/**
 * Created by leha on 2017-04-19.
 */
public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    private long bucketSizeLimit = 10_000;
    private FileBucket[] table = {
            new FileBucket(),new FileBucket(),new FileBucket(),new FileBucket(),
            new FileBucket(),new FileBucket(),new FileBucket(),new FileBucket(),
            new FileBucket(),new FileBucket(),new FileBucket(),new FileBucket(),
            new FileBucket(),new FileBucket(),new FileBucket(),new FileBucket()
    };
    public void setBucketSizeLimit(long n) {bucketSizeLimit = n;}
    public long getBucketSizeLimit() {return bucketSizeLimit;}


    int hash(Long k){
        long h = k;
        h ^= (h >>> 20) ^ (h >>> 12);
        return (int)(h ^ (h >>> 7) ^ (h >>> 4));
    }

    int indexFor(int hash, int length){
        return hash & (length-1);
    }

    Entry getEntry(Long key){
        if (table.length == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry();
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        for (FileBucket e : table) {
            e.remove();
            e = null;
        }
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                }
                else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }
    }
    void addEntry(int hash, Long key, String value, int bucketIndex){
        createEntry(hash, key, value, bucketIndex);
        if ((table[bucketIndex].getFileSize()>getBucketSizeLimit())){
            resize(2 * table.length);
        }
    }

    void createEntry(int hash, Long key, String value, int bucketIndex){
        if (table[bucketIndex]==null) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        }
        else{
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        }
    }


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key)!=null;
    }

    @Override
    public boolean containsValue(String value) {

        if (value == null)
            return false;
        for (FileBucket aTable : table) {
            if (aTable!=null) {
                for (Entry e = aTable.getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key),table.length));
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return null;
        for (FileBucket aTable : table) {
            for (Entry e = aTable.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return aTable.getEntry().getKey();
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }
}
