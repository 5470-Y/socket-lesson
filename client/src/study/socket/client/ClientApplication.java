package study.socket.client;


import study.socket.common.Message;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        // Создаём для локального хоста
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 2222);
        // Создаём клиента, передаём ему адрес и запускаем функционал
        new Client(address).run();
        // То же, но с заданием переменной клиенту
        // Client client = new Client(address);
        // client.run();

        Scanner scanner = new Scanner(System.in);

        Client client = new Client(new InetSocketAddress("localhost", 1111));
        client.run();
    }
}
