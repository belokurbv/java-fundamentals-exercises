package com.bobocode.lesson_4.patterns.abstract_factory.doc;

public class WordDocument implements Document {
    private final String path;

    public WordDocument(String path) {
        System.out.println("Word document: " + path);
        this.path = path;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public String getName() {
        return "docx: " + path;
    }
}
