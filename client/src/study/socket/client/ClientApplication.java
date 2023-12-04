package study.socket.client;


import study.socket.common.Message;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Client client = new Client(new InetSocketAddress("localhost", 1111));
        client.run();
    }
}
