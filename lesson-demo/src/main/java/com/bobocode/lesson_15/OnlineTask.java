package com.bobocode.lesson_15;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class OnlineTask {
    public static void main(String[] args) throws URISyntaxException {
        var client = new RestTemplate();
        var headers = new HttpHeaders();
        headers.set("X-Mood", "sleepy");
        var entity = RequestEntity.post("http://93.175.204.87:8080/users")
                .headers(headers)
                .body(new Name("Bohdan", "Belokur"));
        var response = client.exchange(entity, Name.class);
        System.out.println(response);
    }

    record Name (String firstName, String lastName) {}
}
