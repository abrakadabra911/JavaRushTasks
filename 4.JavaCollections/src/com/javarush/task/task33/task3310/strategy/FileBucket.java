package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Created by leha on 2017-04-19.
 */
public class FileBucket {
    private Path path;
    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        }
        catch (IOException e) {
        }
        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        }
        catch (IOException e) {e.printStackTrace();}
        return 0;
    }

    public void putEntry(Entry entry){
        //— должен сериализовывать переданный entry в файл. Учти, каждый entry может содержать еще один entry.
        try {
            OutputStream fout = Files.newOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(entry);
            oos.flush();
            oos.close();
        }
        catch (Exception e) {}
    }

    public Entry getEntry() {
        // — должен забирать entry из файла. Если файл имеет нулевой размер, вернуть null.
        if (getFileSize() == 0) return null;
        Entry entry = null;
        try {
            InputStream fin = Files.newInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            entry = (Entry) ois.readObject();
            fin.close();
            ois.close();
            return entry;
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void remove(){
        // – удалять файл на который указывает path.
        try {
            Files.delete(path);
        }
        catch (IOException e) {e.printStackTrace();}
    }

}
