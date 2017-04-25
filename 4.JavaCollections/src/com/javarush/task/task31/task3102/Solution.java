package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution
{
    public static List<String> getFileTree(String root) throws IOException
    {
        List<String> allFilesAbsolutePaths = new ArrayList<>();
        Queue<File> queue = new PriorityQueue<>();

        queue.add(new File(root));
        while (!queue.isEmpty())
        {
            File file = queue.poll();
            if (file.isDirectory())
            {
                for (File f : file.listFiles())
                {
                    queue.add(f);
                }
            } else
            {
                allFilesAbsolutePaths.add(file.getAbsolutePath());
            }
        }


        return allFilesAbsolutePaths;
    }
}