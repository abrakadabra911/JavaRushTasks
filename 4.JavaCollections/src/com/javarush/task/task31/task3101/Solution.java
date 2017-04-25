package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
/*
Проход по дереву файлов
*/
public class Solution {
    public static TreeSet<File> Lower50 = new TreeSet<>();
    public static void main(String[] args) {
        for (String s : args)
            System.out.println(s);
        FileOutputStream fos = null;

        try {

            File path = new File(args[0]); //Путь к директории
            File resultFileAbsolutePath = new File(args[1]); //Файл с контекстом всех файлом <50
            File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
            //хз верно ли указывать в конструкторе "allFilesContent", а не "resultFileAbsolutePath"
            //но валидатор принимает, а во втором варианте - нет
             fos = new FileOutputStream(allFilesContent);
            deepSearch(path);
            TreeMap<String, File> fileAndPath = new TreeMap<>();
            for (File f : Lower50)
                fileAndPath.put(f.getName(), f);
            for (Map.Entry<String, File> pair : fileAndPath.entrySet()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(pair.getValue()));
                String s = "";
                while ((s = bufferedReader.readLine()) != null)
                    fos.write((s + "\n").getBytes());
                // fos.write("\n".getBytes());
                bufferedReader.close();
            }
            fos.close();
        }
        catch (Exception e) {

        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }
    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
    public static void deepSearch(File f){
        if(f.isDirectory()){
            for(File ff: f.listFiles()){
                deepSearch(ff);
            }
        }

        else if(f.isFile()){
            if(f.length() > 50)
                FileUtils.deleteFile(f);
            else
                Lower50.add(f);
        }
    }
}