package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

   public static void writeMessage(String message){
       System.out.println(message);
    }

   public static String readString() throws IOException{
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       return reader.readLine();
    }

   /* — просит пользователя выбрать блюдо и добавляет его в список.
    Выведи список всех блюд и попроси пользователя ввести строку — название блюда.
    Введенное ‘exit‘ означает завершение заказа.
    В случае, если введенное блюдо не представлено в меню, выведи сообщение о том, что такого блюда нет и продолжи формирование заказа.
    Исключения ввода-вывода бросай выше, на этом уровне не понятно, что с ними делать.*/

    public static List<Dish> getAllDishesForOrder()throws IOException{
        List<Dish> listOfChoosenDishes = new ArrayList<>();

        writeMessage("Пожалуйста, выберите блюдо:");

        String choosenDish;

        while(!(choosenDish = readString()).equals("exit")) {
            switch (choosenDish){
                case "Fish": listOfChoosenDishes.add(Dish.Fish);break;
                case "Steak": listOfChoosenDishes.add(Dish.Steak);break;
                case "Soup": listOfChoosenDishes.add(Dish.Soup);break;
                case "Juice": listOfChoosenDishes.add(Dish.Juice);break;
                case "Water": listOfChoosenDishes.add(Dish.Water);break;
                case "": writeMessage("Блюдо не выбрано");continue;

                default:  writeMessage("Такого блюда нет.");break;
            }
        }
        return listOfChoosenDishes;
    }
}
//Fish(25), Steak(30), Soup(15), Juice(5), Water(3);