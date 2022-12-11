package com.bobocode.lesson_4.patterns.adapter;

public class ByteContent {
    private final byte[] content;

    public ByteContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

}
