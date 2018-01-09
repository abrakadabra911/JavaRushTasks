package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by leha on 2017-10-12.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String filename = f.getName();
        if (f.isDirectory()) return true;
        if (filename.toUpperCase().endsWith(".HTML") || filename.toUpperCase().endsWith(".HTM")) return true;
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
