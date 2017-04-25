package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {


        Throwable t = new Throwable();
        StackTraceElement trace[] = t.getStackTrace();

        // Глубина стэка должна быть больше 1-го, поскольку интересующий
        // нас элемент стэка находится под индексом 1 массива элементов
        // Элемент с индексом 0 - это текущий метод, т.е. log
        StackTraceElement element = null;
        if (trace.length > 1)
        {
             element = trace[1];
          //  System.out.format("[%s] [%s, %d] %s", element.getClassName(), element.getMethodName(), element.getLineNumber(), "");
        }






        System.out.println(element.getClassName() +": " + element.getMethodName() + ": " +s);

    }
}
