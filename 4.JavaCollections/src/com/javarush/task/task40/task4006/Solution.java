package com.javarush.task.task40.task4006;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
Адрес сервера и параметры для GET-запроса получи из параметра url.
Порт используй дефолтный для http (80).
Классы HttpURLConnection, HttpClient и т.д. не использовать.
Не оставляй закомементированный код.


Требования:
1. Метод getSite должен создавать объект класса Socket с правильными параметрами (String host, int port).
2. Метод getSite должен записать в OutputStream правильный запрос.
3. Метод getSite должен выводить на экран InputStream сокета.
4. Метод getSite не должен использовать HttpURLConnection или HttpClient.
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        int standardHTTPport80 = 80;
        String host = url.getHost();

        try {
            Socket socket = new Socket(host, standardHTTPport80);

            OutputStream out = socket.getOutputStream();

            PrintWriter writer = new PrintWriter(out);
            writer.println("GET "+ url.getPath());
            writer.println("Host: " + url.getHost());
            writer.println("User-Agent: Mozilla/5.0");
            writer.println("Accept: text/html");
            writer.println("Connection: close");
            writer.println("");
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            while (message!=null)
            System.out.println(message);

            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}