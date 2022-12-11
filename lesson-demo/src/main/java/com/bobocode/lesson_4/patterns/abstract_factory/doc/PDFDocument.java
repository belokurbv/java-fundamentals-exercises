package com.bobocode.lesson_4.patterns.abstract_factory.doc;

public class PDFDocument implements Document {
    private final String path;

    public PDFDocument(String path) {
        System.out.println("PDF document: " + path);
        this.path = path;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public String getName() {
        return "Default,Metadata,Text";
    }
}
