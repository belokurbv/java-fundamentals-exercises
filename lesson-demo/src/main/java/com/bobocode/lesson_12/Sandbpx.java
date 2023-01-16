package com.bobocode.lesson_12;

import lombok.SneakyThrows;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Sandbpx {
    @SneakyThrows
    public static void main(String[] args) {
        ServerSocket serverSocket;
        try (var socket = new Socket("93.175.204.87", 8090)) {
            var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = """
                    GET /hello?name=bohdan_belokur HTTP/1.1
                    Host: 93.175.204.87:8090
                    User-Agent: Bobocode User Agent
                    """;
            writer.println(str);
            writer.flush();
        }
    }
}
