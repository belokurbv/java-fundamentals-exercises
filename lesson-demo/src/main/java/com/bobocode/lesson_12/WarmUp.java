package com.bobocode.lesson_12;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.stream.Collectors;

public class WarmUp {
    @SneakyThrows
    public static void main(String[] args) {
        send();
    }

    @SneakyThrows
    static void send() {
        try (var socket = new Socket("93.175.204.87", 8080)) {
            var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var payload = """
                            {
                              "firstName": "bohdan",
                              "lastName": "belokur"
                            }
                            """;
            var str = String.format("""
                            POST /warmup/users HTTP/1.1
                            Host: 93.175.204.87:8080
                            User-Agent: Bobocode User Agent
                            Content-Type: application/json
                            Content-Length: %s
                                                
                            %s
                            """, payload.length(), payload);
            System.out.println(str);
            writer.println(str);
            writer.flush();
            var buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (buff.readLine() != null) {
                System.out.println(buff.readLine());
            }
        }
    }
}
