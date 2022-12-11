package com.bobocode.lesson_4.patterns.adapter;

public class ContentAdapter extends StringContent {
    public ContentAdapter(ByteContent byteContent) {
        super(new String(byteContent.getContent()));
    }
}
