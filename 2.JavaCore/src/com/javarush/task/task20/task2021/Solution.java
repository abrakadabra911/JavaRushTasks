package com.javarush.task.task20.task2021;

import java.io.*;

/* 
Запрет сериализации
*/
public class Solution implements Serializable {
    public static class SubSolution extends Solution {
        SubSolution(){
        }

        private void readObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException {
           // objectStream.defaultReadObject();
            throw new NotSerializableException();

        }
        private void writeObject(ObjectOutputStream out) throws IOException {
          //  out.defaultWriteObject();
            throw new NotSerializableException();
        }
    }

    public static void main(String[] args) {
        SubSolution solut = new SubSolution();


    }
}
