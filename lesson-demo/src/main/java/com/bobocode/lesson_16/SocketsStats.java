package com.bobocode.lesson_16;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class SocketsStats {
    public static void main(String[] args) {
        try (var socket = new Socket("93.175.204.87", 8080)){
            var writer = new PrintWriter(socket.getOutputStream());
            //var writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            var mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(new Stats("Bohdan", "Belokur", "Petros", 3, 30));
            var str = """
                    POST /training/stats HTTP/1.1
                    Host: 93.175.204.87:8090
                    User-Agent: Bobocode User Agent
                    Content-Type: application/json
                    Content-Length: %s
                    Connection: close
                    
                    %s
                    """.formatted(json.length(), json);
            System.out.println(str);
            writer.println(str);
            writer.flush();
            var buff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (buff.readLine() != null) {
                System.out.println(buff.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    record Stats(String firstName, String lastName, String team,
                 Integer trainingDaysPerWeek,
                 Integer minutesPerTrainingDay) { }
}
