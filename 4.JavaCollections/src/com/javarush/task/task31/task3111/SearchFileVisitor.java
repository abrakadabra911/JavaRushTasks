package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    String partOfName = "";
    boolean name = false;
    String partOfContent = "";
    boolean content = false;
    long minSize = 0;
    boolean misize = false;
    long maxSize = 0;
    boolean masize = false;

    ArrayList<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] data = Files.readAllBytes(file);
        String line = new String(data, StandardCharsets.UTF_8);
        String filename = file.toString();
        long sizeOFfile = file.toFile().length();
        boolean a = (name && filename.contains(partOfName)) || !name;
        boolean b = (content && line.contains(partOfContent)) || !content;
        boolean c = (misize && (sizeOFfile > minSize)) || !misize;
        boolean d = (masize && (sizeOFfile < maxSize)) || !masize;

        if (a && b && c && d)  foundFiles.add(file);

        return FileVisitResult.CONTINUE;
    }


    public List<Path> getFoundFiles() {

        return this.foundFiles;
    }


    public void setPartOfName(String s) {
        this.partOfName = s;
        this.name = true;
    }
    public void setPartOfContent(String s) {
        this.partOfContent = s;
        this.content = true;
    }
    public void setMinSize(long s) {
        this.minSize = s;
        this.misize = true;
    }
    public void setMaxSize(long s) {
        this.maxSize = s;
        this.masize = true;
    }
}


