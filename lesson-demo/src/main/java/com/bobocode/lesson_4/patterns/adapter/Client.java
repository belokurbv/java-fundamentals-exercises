package com.bobocode.lesson_4.patterns.adapter;

public class Client {
    public static void main(String[] args) {
        var byteContent = new ByteContent("Hello".getBytes());
        var adapter = new ContentAdapter(byteContent);
        System.out.println(adapter.getContent());

    }
}
