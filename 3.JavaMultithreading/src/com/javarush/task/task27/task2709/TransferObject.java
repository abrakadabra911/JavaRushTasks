package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
        while (!isValuePresent){
            try {
                this.wait();
            }
            catch (InterruptedException e){}
        }
        System.out.println("Got: " + value);
        notifyAll();
        isValuePresent = false;

        return value;
    }

    public synchronized void put(int value) {
        while (isValuePresent){
            try {
                this.wait();
            }
            catch (InterruptedException e){}
        }
        this.value = value;
        System.out.println("Put: " + value);
        notifyAll();
        isValuePresent = true;

    }
}
