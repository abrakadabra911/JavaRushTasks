package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args)throws IOException , ParseException {
        String fileName = args[0];
        String input;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
        String split[];
        String name;
        String date;
        while ((input = fileReader.readLine()) != null) {
            split = input.split(" ");
            name = split[0];
            if (split.length > 4){
                for (int i = 1; i < split.length - 3; i++) {
                    name+= " " + split[i];
                }
            }
            int day = Integer.parseInt(split[split.length - 3]);
            int month = Integer.parseInt(split[split.length - 2]);
            int year = Integer.parseInt(split[split.length - 1]);

            date = ""+day +" "+ month +" "+ year ;
            Person person = new Person(name, simpleDateFormat.parse(date));

            PEOPLE.add(person);
        }

        fileReader.close();




    }
}
