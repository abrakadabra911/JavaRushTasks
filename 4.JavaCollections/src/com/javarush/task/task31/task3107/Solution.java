package com.javarush.task.task31.task3107;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) throws IOException{
        File f = new File(pathToFile);

try {

    boolean a = Files.isHidden(Paths.get(f.getAbsolutePath()));
    boolean b = Files.isExecutable(Paths.get(f.getAbsolutePath()));
    boolean c = Files.isDirectory(Paths.get(f.getAbsolutePath()));
    boolean d = Files.isWritable(Paths.get(f.getAbsolutePath()));

    this.fileData = new ConcreteFileData(a, b, c, d);
}
catch (Exception e) {
    this.fileData = new NullFileData(new Exception());
        System.out.print("неизвестная ошибка");
}

    }

    public FileData getFileData() {
        return fileData;
    }
}
