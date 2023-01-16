package com.bobocode.lesson_12;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class SocketParser {
    public static final String URL = "http://93.175.204.87:8081/mars-photos/api/v1/rovers/curiosity/photos?sol=14" +
            "&api_key=DEMO_KEY";

    @SneakyThrows
    public static void main(String[] args) {
        try (var socket = new Socket("93.175.204.87", 8081)) {
            var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = """
                    GET /mars-photos/api/v1/rovers/curiosity/photos?sol=14&api_key=nKvbQ9bwRfcg2KUCjRww9mxm3IM3rJi0U6cfwJvG HTTP/1.1
                    Host: 93.175.204.87:8090
                    User-Agent: Bobocode User Agent
                    Accept: application/json
                    """;
            writer.println(str);
            writer.flush();
            var buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (buff.readLine() != null) {
                System.out.println(buff.readLine());
            }
            //
        }
    }
}
