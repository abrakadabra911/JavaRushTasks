package task3702.male;

import com.javarush.task.task37.task3702.Human;

/**
 * Created by leha on 2017-03-23.
 */
public class MaleFactory {
    public Human getPerson (int age){
        if (age<0)return null;
        if (age <= KidBoy.MAX_AGE) return new KidBoy();
        if (age <= TeenBoy.MAX_AGE) return new TeenBoy();
        else{return new Man();}
    }
}
