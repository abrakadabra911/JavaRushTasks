package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        Object o= null;
        try {
            o=objectStream.readObject();
            if(o instanceof A) return (A)o;
            if(o instanceof B) return (B)o;
           else return null;
        }
        catch (Exception e)  {
            System.out.print("ашыпка");
            return null;
        }

    }
    private void readObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException {
        objectStream.defaultReadObject();

    }

    public class A implements Serializable{

    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
