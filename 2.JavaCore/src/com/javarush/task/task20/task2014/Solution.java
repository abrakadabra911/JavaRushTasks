package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        System.out.println(new Solution(4));

        File file = new File("D:\\test\\m.txt");
        FileOutputStream fileout = new FileOutputStream(file);
        Solution savedObject = new Solution(4);

        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(fileout);
        oos.writeObject(savedObject);
        oos.close();

        Solution loadedObject = new Solution(4);
        //Recreating the instance by reading the serialized object data add
        FileInputStream filein = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(filein);

        loadedObject = (Solution) ois.readObject();
        ois.close();

        System.out.println(savedObject.string.equals(loadedObject.string));

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient  private Date currentDate;
    transient  private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
      public String toString() {
        return this.string;
    }
}
