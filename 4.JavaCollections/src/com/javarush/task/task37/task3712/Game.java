package com.javarush.task.task37.task3712;

public abstract class Game implements Runnable{

    abstract public void prepareForTheGame();

    abstract public void playGame();

    abstract public void congratulateWinner();

    @Override
    public void run() {
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }
}
