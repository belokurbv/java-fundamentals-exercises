package com.bobocode.lesson_4.patterns.abstract_factory;

import com.bobocode.lesson_4.patterns.abstract_factory.factory.DocumentPreviewFactory;

public class Client {
    public static void main(String[] args) {
        var path = "path_to_file.pdf";
        var factory = DocumentPreviewFactory.getInstance(path);
        System.out.println(factory.create(path));
    }
}
