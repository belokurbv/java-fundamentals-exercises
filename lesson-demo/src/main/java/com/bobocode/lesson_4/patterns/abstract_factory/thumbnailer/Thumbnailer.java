package com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.Document;

public interface Thumbnailer<T extends Document> {
    byte[] generate(T t);
}
