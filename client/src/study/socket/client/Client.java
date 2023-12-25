package study.socket.client;

import study.socket.common.ConnectionService;
import study.socket.common.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable{
    private final InetSocketAddress  remoteAddress;

    public Client(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }


    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите текст или /exit для выхода");
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("/exit")) break;
            try (Socket socket = new Socket()) {
                socket.connect(remoteAddress);
                try (ConnectionService service = new ConnectionService(socket)){
                   service.writeMessage(new Message(text));
                   Message message = service.readMessage();
                   System.out.println(message.getText());
                } catch (IOException e){
                    System.out.println("Сервер перестал отвечать");
                } /*catch (Exception e) {
                    throw new  RuntimeException(e);
                }*/
            } catch (IOException e) {
                System.out.println("Сервер не доступен");
            }
        }
    }
}
