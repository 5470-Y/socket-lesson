package study.soket.server;

import study.socket.common.ConnectionService;
import study.socket.common.Message;

import java.io.IOException;
import java.net.ServerSocket;


public class Server implements Runnable{
        private final int port;


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
                    System.out.println(message.getText());
                    connectionService.writeMessage(new Message("from server"));
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
}
