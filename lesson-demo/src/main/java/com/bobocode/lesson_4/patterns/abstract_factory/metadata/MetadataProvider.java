package com.bobocode.lesson_4.patterns.abstract_factory.metadata;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.Document;

import java.util.List;

public interface MetadataProvider<T extends Document> {
    List<String> getKeywords(T t);
}
