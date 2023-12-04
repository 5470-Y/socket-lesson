package study.soket.server;

import study.socket.common.Message;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerApplication {

    public static void main(String[] args) {

        Server server = new Server(2222);
        server.run();





    }
}
