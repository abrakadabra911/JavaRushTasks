package com.javarush.task.task29.task2909.car;

/**
 * Created by leha on 2017-06-10.
 */
public class Sedan extends Car {
    public Sedan(int numberOfPassengers) {
        super(1, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SEDAN_SPEED;
    }
}
