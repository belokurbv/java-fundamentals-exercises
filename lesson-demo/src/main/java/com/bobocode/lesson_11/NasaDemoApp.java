package com.bobocode.lesson_11;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class NasaDemoApp {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String path = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=300&api_key=nKvbQ9bwRfcg2KUCjRww9mxm3IM3rJi0U6cfwJvG";
        var restTemplate = new RestTemplate();
        var response = restTemplate.getForObject(path, JsonNode.class);
        var max = StreamSupport.stream(response.get("photos").spliterator(), false)
                .map(value -> {
                    var img = value.get("img_src");
                    var headers = restTemplate.headForHeaders(img.asText());
                    var h2 = restTemplate.headForHeaders(headers.getLocation());
                    return new Picture(img.asText(), h2.getContentLength());
                }).max(Comparator.comparing(
                        obj -> obj.length
                ));
        System.out.println(max);
    }

    record Picture(String url, long length) { }

    record Photos(List<Item> photos) {}
    record Item(String img_src) {}
}
