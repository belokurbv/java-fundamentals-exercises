package com.bobocode.lesson_12;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketRequest {
    public static final String URL = "http://93.175.204.87:8080/hello";

    @SneakyThrows
    public static void main(String[] args) {
        var cookie = "";
        try (var socket = new Socket("93.175.204.87", 8080)) {
            var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = """
                    GET /hello?name=bohdan HTTP/1.1
                    Host: 93.175.204.87:8090
                    User-Agent: Bobocode User Agent
                    X-Mood: norm
                    Accept: application/json
                    Connection: closed
                    """;
            writer.println(str);
            writer.flush();
            var buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (buff.readLine() != null) {
                var line = buff.readLine();
                if (line.contains("Set-Cookie")) {
                    cookie = line;
                }
            }
            //
        }
        System.out.println(2);
        try (var socket = new Socket("93.175.204.87", 8080)) {
            var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var str = """
                    GET /hello HTTP/1.1
                    Host: 93.175.204.87:8090
                    User-Agent: Bobocode User Agent
                    X-Mood: norm
                    Accept: application/json
                    """;
            str = str + cookie;
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
