package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap()throws Exception {
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(filename);


        load(inputStream);
        inputStream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        DataOutputStream dOS = new DataOutputStream(outputStream);
        Properties prop = new Properties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            prop.put(entry.getKey(), entry.getValue());
        }
        prop.store(dOS, "Where does this go?");

        dOS.flush();
        dOS.close();

    }

    public void load(InputStream inputStream) throws Exception {

        InputStream  instr = inputStream;
        Properties prop = new Properties();

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file not found in the classpath");
        }
        for (final String name: prop.stringPropertyNames())
            properties.put(name, prop.getProperty(name));


    }

    public static void main(String[] args) {

    }
}
