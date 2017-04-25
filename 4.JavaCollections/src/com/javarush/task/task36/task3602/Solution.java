package com.javarush.task.task36.task3602;


import org.reflections.Reflections;

//import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Reflections reflections = new Reflections("D:.JavaRushTasks.4.JavaCollections.src.com.javarush.task.task36.task3602");

        Set<Class<? extends List>> allClasses = reflections.getSubTypesOf(List.class);
        for(Class y : allClasses) {
            System.out.println(y.getSimpleName());
        }

        Class clazz = null;
        /*
        Class start = ArrayList.class;
        Class[ ] classes  = start.getDeclaredClasses();
for(Class x : classes) {
    try{
        ArrayList list = (ArrayList)x.newInstance();
        list.get(0);
    }
    catch (IndexOutOfBoundsException e) {
        clazz = x;
    }
    catch (InstantiationException e) { System.out.println("ошибка 1");
    }
    catch (IllegalAccessException e) {System.out.println("ошибка 2");
    }

}
*/
        return clazz;
    }


}
