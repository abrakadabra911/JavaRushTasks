package com.javarush.task.task29.task2909.human;

/**
 * Created by leha on 2017-06-09.
 */
public class Soldier extends Human{
    public Soldier(String name, int age) {
        super(name, age);
    }
    public void fight() {
    }
    public void live() {
        fight();
    }
}
