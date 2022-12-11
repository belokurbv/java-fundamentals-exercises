package com.bobocode.lesson_4.patterns.abstract_factory;

import java.util.Arrays;
import java.util.List;

public class PreviewModel {
    List<String> keywords;

    byte[] thumbnail;

    public PreviewModel(List<String> keywords, byte[] thumbnail) {
        this.keywords = keywords;
        this.thumbnail = thumbnail;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "PreviewModel{" +
                "keywords=" + keywords +
                ", thumbnail=" + Arrays.toString(thumbnail) +
                '}';
    }
}
