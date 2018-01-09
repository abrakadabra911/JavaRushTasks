package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;


/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("1.1.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {

        boolean printDate = false;
        boolean printTime = false;

        LocalDate localDate = null;
        LocalTime localTime = null;
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("H:m:s");

        if (date.contains(" ")) {
            String[] splitedDate = date.split(" ");
            localDate = LocalDate.parse(splitedDate[0], dateformatter);
            localTime = localTime.parse(splitedDate[1], timeformatter);
            printTime = true;
            printDate = true;
        } else if (date.contains(":")) {
            localTime = localTime.parse(date, timeformatter);
            printTime = true;
        } else if (date.contains(".")) {
            localDate = LocalDate.parse(date, dateformatter);
            printDate = true;
        }

        if (printDate) {
            System.out.println("День: " + localDate.getDayOfMonth());
            System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
            System.out.println("День месяца: " + localDate.getDayOfMonth());
            System.out.println("День года: " + localDate.getDayOfYear());
            System.out.println("Неделя месяца: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth()));
            System.out.println("Неделя года: " + localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear()));
            System.out.println("Месяц: " + localDate.getMonthValue());
            System.out.println("Год: " + localDate.getYear());
        }

        if (printTime) {
            System.out.println("AM или PM: " + (localTime.getHour()<12 ? "AM" : "PM"));
            System.out.println("Часы: " + (localTime.getHour()>12? localTime.getHour() - 12: localTime.getHour()));
            System.out.println("Часы дня: " + localTime.getHour());
            System.out.println("Минуты: " + localTime.getMinute());
            System.out.println("Секунды: " + localTime.getSecond());
        }
    }
}
