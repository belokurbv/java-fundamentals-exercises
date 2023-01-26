package com.bobocode.lesson_14;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.stream.StreamSupport;

public class LargestPicture {
    public static String path = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=300&api_key=DEMO_KEY";

    @SneakyThrows
    public static void main(String[] args) {
        var client = new RestTemplate();
        var response = client.getForObject(path, JsonNode.class);
        var value = StreamSupport.stream(response.get("photos").spliterator(), false)
                .map(node -> node.get("img_src"))
                .map(src -> {
                    var size = client.headForHeaders(src.asText()).getContentLength();
                    return new Picture(src.asText(), size);
                }).max(Comparator.comparing(Picture::size)).get();
        System.out.println(value);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        client.postForLocation("http://93.175.204.87:8080/mars/pictures/largest", new Result(new User("bohdan",
                "belokur"), value), String.class);
    }

    record Picture(String url, Long size) {
    }

    record Result(User user, Picture picture) {}
    record User(String firstName, String lastName) {}
}
