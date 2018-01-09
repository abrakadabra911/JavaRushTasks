package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import java.io.IOException;
import java.net.Socket;


/**
 * Created by leha on 2017-06-19.
 */
public class Client {
    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            System.out.println(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            System.out.println("участник с именем " + userName + " присоеденился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            System.out.println("участник с именем " + userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            try {
                while (true) {
                    Message message = connection.receive();
                    if (message.getType().equals(MessageType.NAME_REQUEST)) {
                        connection.send(new Message(MessageType.USER_NAME, getUserName()));
                        continue;
                    }
                    if (message.getType().equals(MessageType.NAME_ACCEPTED)) {
                        notifyConnectionStatusChanged(true);
                        break;
                    } else {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
            catch (Exception e) { throw new IOException("Unexpected MessageType");}
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            try {
                while (true) {
                    Message message = connection.receive();
                    if (message.getType().equals(MessageType.TEXT)) {
                        processIncomingMessage(message.getData());
                        continue;
                    }
                    if (message.getType().equals(MessageType.USER_ADDED)) {
                        informAboutAddingNewUser(message.getData());
                        continue;
                    }
                    if (message.getType().equals(MessageType.USER_REMOVED)) {
                        informAboutDeletingNewUser(message.getData());
                        continue;
                    } else {
                        throw new IOException("Unexpected MessageType");
                    }

                }
            }
            catch (Exception e) { throw new IOException("Unexpected MessageType");}
    }
        public void run() {
            String serveradress = getServerAddress();
            int serverport = getServerPort();
            try {
                Socket socket = new Socket(serveradress, serverport);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException e) {
                System.out.println("ошибка IOException в главном потоке клиента");
                notifyConnectionStatusChanged(false);
            }
            catch (ClassNotFoundException e) {
                System.out.println("ошибка ClassNotFoundException в главном потоке клиента");
                notifyConnectionStatusChanged(false);
            }


        }
    }





    protected Connection connection;
   volatile private boolean clientConnected = false;

    protected String getServerAddress() {
        System.out.println("Введите адрес сервера: ");
       return ConsoleHelper.readString();
    }
    protected int getServerPort() {
        System.out.println("Введите порт: ");
        return ConsoleHelper.readInt();
    }
    protected String getUserName() {
        System.out.println("Введите имя пользователя: ");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole() {return true;}

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }
    protected void sendTextMessage(String text) {
        try{
        connection.send(new Message(MessageType.TEXT, text));}
            catch (IOException e) {
                System.out.println("Произошла ошибка подключения");
                clientConnected = false;
            }
    }
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this) {this.wait();}
        }
        catch (InterruptedException e){ System.out.println("Произошла ошибка ожидания подключения");}
        if(clientConnected) System.out.println("Соединение установлено. Для выхода наберите команду ‘exit’.");
        else{ System.out.println("Произошла ошибка во время работы клиента.");}

        String text =  ConsoleHelper.readString();
        while (!text.equals("exit") && clientConnected == true) {
            if (shouldSendTextFromConsole()) sendTextMessage(text);
            text =  ConsoleHelper.readString();
        }
        clientConnected = false;

    }
    public static void main(String[] args)throws IOException {
        Client client = new Client();
        client.run();
        Client client2 = new Client();
        client2.run();
        Client client3 = new Client();
        client3.run();
        Client client4 = new Client();
        client4.run();
    }
}
