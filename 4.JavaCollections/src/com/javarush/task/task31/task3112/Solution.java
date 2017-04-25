package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*
Загрузчик файлов
*/
public class Solution {

    // программа работает ОХУЕННО !!!!
    // программа работает ОХУЕННО !!!!
    // программа работает ОХУЕННО !!!! Алексей заяц

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://yastatic.net/morda-logo/i/citylogos/yandex19-logo-ru.png", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords, Charset.defaultCharset())) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        Path tempFile = Files.createTempFile(null,null);
        String fileName = urlString.substring(urlString.lastIndexOf("/")+1);
        Files.copy(url.openStream(), tempFile,  StandardCopyOption.REPLACE_EXISTING);
        Path target = Paths.get(downloadDirectory.toString(),fileName);
        Files.move(tempFile, target, StandardCopyOption.REPLACE_EXISTING);
        return target;
    }
}
