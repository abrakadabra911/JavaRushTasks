package task3702.female;

import com.javarush.task.task37.task3702.Human;

/**
 * Created by leha on 2017-03-23.
 */
public class FemaleFactory {
    public Human getPerson (int age){
        if (age<0)return null;
        if (age <= KidGirl.MAX_AGE) return new KidGirl();
        if (age <= TeenGirl.MAX_AGE) return new TeenGirl();
        else{return new Woman();}
    }

}
