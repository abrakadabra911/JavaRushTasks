package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;
import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.Man;
import com.javarush.task.task37.task3702.male.TeenBoy;

/**
 * Created by leha on 2017-03-23.
 */
public class FemaleFactory implements AbstractFactory {
    public Human getPerson (int age){
        if (age<0)return null;
        if (age <= KidGirl.MAX_AGE) return new KidGirl();
        if (age <= TeenGirl.MAX_AGE) return new TeenGirl();
        else{return new Woman();}
    }

}
