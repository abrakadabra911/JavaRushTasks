package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private String fileName;
   transient private FileOutputStream stream;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }
   // public Solution(){}

   public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(this.fileName ,true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args)throws  Exception, ClassNotFoundException {

        Solution solution = new Solution("D:\\test.x.txt");
        solution.writeObject("kakashka");

        FileOutputStream filestr = new FileOutputStream("D:\\test.x.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(filestr);
        outputStream.writeObject(solution);

        //loading
        FileInputStream fiStream = new FileInputStream("D:\\test.x.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Solution loadedObject = (Solution) objectStream.readObject();
        loadedObject.writeObject("huj");
        outputStream.writeObject(loadedObject);


        //Attention!!
        System.out.println();
    }
}
