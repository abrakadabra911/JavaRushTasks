package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        ObjectMapper om = new ObjectMapper();
        File file = new File(fileName);
        T t = om.readValue(file, clazz);
        return t;
    }

    public static void main(String[] args) {

    }
}
