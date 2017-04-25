package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) throws FileNotFoundException{
        Solution solution = new Solution();
        File fileToProceed = new File("D:\\SecretFolder\\result.txt");
        FileReader fileInput = new FileReader(fileToProceed);
        System.out.println(fileInput.getEncoding());

        Properties  properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

         properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);



        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);


    }

    public Properties getProperties(String fileName) {
       // fileName = fileName.replaceAll("/", File.separator);
        System.out.println(fileName);
        //файл, который хранит свойства нашего проекта

//создаем объект Properties и загружаем в него данные из файла.
        Properties properties = new Properties();
        try {
            if (fileName.endsWith(".xml")) {
                properties.loadFromXML(new FileInputStream(fileName));
            }
            else {
                properties.load(new FileInputStream(fileName));
            }
            return properties;
        }
        catch (Exception e) {
            System.out.println("ошибка Exception");
            return new Properties();
        }


    }
}
