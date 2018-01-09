package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by leha on 2017-06-18.
 */
public class Server {
    public static void main(String[] args)throws IOException {
        int port = ConsoleHelper.readInt();

        try(ServerSocket serverSocket = new ServerSocket(port)) {

            ConsoleHelper.writeMessage("Сервер запущен.");

              while (true) {
                  new Handler(serverSocket.accept()).start();
              }
             }
             catch (Exception e) {
                 ConsoleHelper.writeMessage("Ошибка сокета сервера");
            }
    }

    private static Map<String, Connection> connectionMap= new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        Iterator it = connectionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String clientname = (String)pair.getKey();
            Connection con = (Connection)pair.getValue();
            try{con.send(message);}
            catch (IOException e) {ConsoleHelper.writeMessage("Сообщение не было отправлено.");}
        }
    }

private static class Handler extends Thread{
    private Socket socket;
    public Handler(Socket socket) {
        this.socket = socket;
    }
    private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
        String result = null;
        Message message = new Message(MessageType.NAME_REQUEST);
        connection.send(message);
        Message clientmessage = connection.receive();
        String username = clientmessage.getData();
        MessageType messageType = clientmessage.getType();

        while (messageType != MessageType.USER_NAME || username.isEmpty() || connectionMap.containsKey(username)) {
            message = new Message(MessageType.NAME_REQUEST);
            connection.send(message);
             clientmessage = connection.receive();
             username = clientmessage.getData();
             messageType = clientmessage.getType();
        }
        connectionMap.put(username, connection);
        connection.send(new Message(MessageType.NAME_ACCEPTED));
        result = username;
        return result;
    }
    private void sendListOfUsers(Connection connection, String userName) throws IOException {
        Iterator it = connectionMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String clientname = (String)pair.getKey();
          Message message = new Message(MessageType.USER_ADDED, clientname);
           if(!clientname.equals(userName))connection.send(message);
        }
    }
    private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
        while (true) {
            Message receive = connection.receive();
            MessageType type = receive.getType();
        if (type == MessageType.TEXT) Server.sendBroadcastMessage(receive);
        else {ConsoleHelper.writeMessage("Ошибка");
        }
    }
    }
    public void run() {
        System.out.println("Установлено новое соединение с " + socket.getRemoteSocketAddress());
        String userName = "";
        try (Connection connection = new Connection(socket)) {
            userName = serverHandshake(connection);
            sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
            sendListOfUsers(connection, userName);
            serverMainLoop(connection, userName);
        }
        catch (IOException e) {ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");}
        catch (ClassNotFoundException e){ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом.");}
        connectionMap.remove(userName);
        sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
        ConsoleHelper.writeMessage("соединение с удаленным адресом закрыто.");
    }

}

}
