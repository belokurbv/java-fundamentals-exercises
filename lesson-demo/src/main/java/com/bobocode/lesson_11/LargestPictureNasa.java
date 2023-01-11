package com.bobocode.lesson_11;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.math3.util.Pair;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class LargestPictureNasa {
    public static final String URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=300&api_key=nKvbQ9bwRfcg2KUCjRww9mxm3IM3rJi0U6cfwJvG";

    static RestTemplate client = new RestTemplate();

    static Optional<Pair<URI, Long>> getImage(boolean isParallel, List<String> urls) {
        var stream = isParallel ? urls.stream().parallel() : urls.stream();
        return stream.map(client::headForHeaders)
                .map(header -> new Pair<>(header.getLocation(),
                        client.headForHeaders(header.getLocation()).getContentLength()))
                .max(Comparator.comparing(Pair::getValue));
    }

    public static void main(String[] args) {
        var photos = client.getForObject(URL, JsonNode.class);
        long start = System.currentTimeMillis();

        var urls = StreamSupport.stream(photos.get("photos").spliterator(), true)
                .map(node -> node.get("img_src").asText()).toList();

        getImage(false, urls).ifPresent(System.out::println);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        getImage(true, urls).ifPresent(System.out::println);
        System.out.println(System.currentTimeMillis() - start);
    }
}
