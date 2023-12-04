package study.soket.server;

import study.socket.common.ConnectionService;
import study.socket.common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Server implements Runnable{
        private final int port;
        private static int requestsCount;
        Message[] messages;


    public Server(int port) {
        this.port = port;
    }


    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Сервер запущен");
            while (true) {
                try (ConnectionService connectionService = new ConnectionService(serverSocket.accept())) {                    //  когда соединение принято, создаётся клиентский сокет
                    Message message = connectionService.readMessage();
                    if (isCommand(message)) {
                        pickOption(message);
                    } else {
                        System.out.println(message.getText());
                        connectionService.writeMessage(new Message("from server"));
                        requestsCount++;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Ошибка подключения клиента");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибка создания serverSocket."); // Скорее всего занят порт
        }
    }

    public void help(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("menu.txt"));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Список команд не найден.");
        }
    };
    public void ping(){
        Socket socket = new Socket();
        socket.isConnected();
    };

    public void requests(){
        System.out.println("Обработано " + requestsCount + " запроса(-ов)");
    };
    public void popular(){
        var messagesStream = Arrays.stream(messages)
                .collect(Collectors.groupingBy(
                        Message::getText,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println(messagesStream);
    };

    public boolean isCommand(Message message) {
        return (message.getText().equalsIgnoreCase("/help")
                || (message.getText().equalsIgnoreCase("/ping")
                || message.getText().equalsIgnoreCase("/requests")
                || message.getText().equalsIgnoreCase("/popular")));
    }

    public void pickOption (Message message) {
        switch (message.getText().toLowerCase()) {
            case "/help" -> help();
            case "/ping" -> ping();
            case "/requests" -> requests();
            case "/popular" -> popular();
        }
    }


}
