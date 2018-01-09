package com.javarush.task.task40.task4009;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/* 
Buon Compleanno!
Реализуй метод weekDayOfBirthday.
Метод должен возвращать день недели на итальянском языке, в который будет (или был) день рождения в определенном году.
Пример формата дат смотри в методе main.

Пример:
1) Для "30.05.1984" и "2015" метод должен вернуть: sabato
2) Для "1.12.2015" и "2016" метод должен вернуть: gioved?

Выполни задание, используя Java 8 DateTime API.


Требования:
1. Используй статический метод parse класса LocalDate.
2. Используй статический метод parse класса Year.
3. Используй локаль Locale.ITALIAN.
4. Метод weekDayOfBirthday должен возвращать правильный день недели для передаваемых параметров.
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.2017", "2016"));
    }

    public static String weekDayOfBirthday(String birthday, String year) {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        Year searchingYear = Year.parse(year);
        LocalDate localDate = LocalDate.parse(birthday, dateformatter);
        LocalDate localDate2;
        localDate2 = localDate.plusYears(searchingYear.getValue() - localDate.getYear());

        return (localDate2.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN));
    }
}
