package com.javarush.task.task29.task2909.car;

/**
 * Created by leha on 2017-06-10.
 */
public class Cabriolet extends Car{
    public Cabriolet(int numberOfPassengers) {
        super(2, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_CABRIOLET_SPEED;
    }
}
