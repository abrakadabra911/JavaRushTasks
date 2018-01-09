package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Data data = (Data)(new ArrayList());
    }

    public void methodThrowsNullPointerException() {
        new ArrayList<>(null);
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
