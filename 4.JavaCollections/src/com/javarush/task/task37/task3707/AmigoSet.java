package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

/**
 * Created by leha on 2017-03-20.
 */
public class AmigoSet <E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private final static Object PRESENT = new Object();
    private transient HashMap<E,Object> map;
    public AmigoSet ()  {
        map = new HashMap<E,Object>();
    }
    public AmigoSet (Collection<? extends E> collection)  {
        int capasity = Math.max(16, (int)(collection.size()/.75f)+1);
        map = new HashMap<>(capasity);
this.addAll(collection);
    }

    @Override
    public Iterator iterator() {

        return map.keySet().iterator();
    }

    @Override
    public boolean isEmpty() {
        if (map.isEmpty())  return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e,PRESENT);
    }

    @Override
    public Object clone() {
        AmigoSet<E> amigoSet;
        HashMap<E,Object> map2;
        try {
           amigoSet = (AmigoSet)super.clone();
            map2 = (HashMap)map.clone();
            amigoSet.map = map2;
        }
        catch (Exception e){
            throw new InternalError();
        }

        return amigoSet;

    }


    private final void writeObject(ObjectOutputStream x) throws IOException {
        try {
            x.defaultWriteObject();

            int size = HashMapReflectionHelper.callHiddenMethod(map, "size");
            float loadfactor = (float).75;
            int capasity = Math.max(16, (int)(size/.75f)+1);
            x.writeObject(capasity);
            x.writeObject(loadfactor);

            x.close();

        }catch(IOException i) {
            i.printStackTrace();
        }
    }
    private final void readObject(ObjectInputStream x) throws IOException {
        try {
            x.defaultReadObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        AmigoSet<String> amigo  = new AmigoSet<>();
        FileOutputStream fileOut = new FileOutputStream("/tmp/employee.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);

    }


}
