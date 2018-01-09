package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
               return !dishes.isEmpty()? "Your order: " + dishes + " of " + tablet: "";
    }

    public int getTotalCookingTime(){
        int time=0;
        for(int i = 0; i < dishes.size(); i++) {
            time += dishes.get(i).getDuration();
        }
        return time;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }
}
