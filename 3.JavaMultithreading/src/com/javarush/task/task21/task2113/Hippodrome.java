package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leha on 2017-05-09.
 */
public class Hippodrome {
    public Hippodrome (List<Horse> horses) {
        this.horses = horses;
    }
    private List<Horse> horses;
    public List<Horse> getHorses() {
        return horses;
    }
    public static Hippodrome game;

    public void run(){
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for (Horse x: horses) x.move();
    }
    public void print(){
        for (Horse x: horses) {
            x.print();
        }
        for (int i = 0; i < 10; i++) System.out.println();
    }
    public Horse getWinner(){
        Horse result = new Horse("",0,0);
        for (Horse x: horses) {
            result = x.getDistance()>result.getDistance()? x:result;
        }
        return result;
    }
    public void printWinner() { System.out.println("Winner is "+ getWinner().getName()+ "!");}


    public static void main(String[] args) {
        Horse one = new Horse("Murka",3,0);
        Horse two = new Horse("Burka",3,0);
        Horse three = new Horse("Churka",3,0);
        List<Horse> hrs = new ArrayList<>();
        hrs.add(one);
        hrs.add(two);
        hrs.add(three);
        Hippodrome.game = new Hippodrome(hrs);
        game.run();
        game.printWinner();
    }
}
