package com.bobocode.lesson_4.patterns.abstract_factory.factory;

import com.bobocode.lesson_4.patterns.abstract_factory.PreviewModel;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.Document;

public interface DocumentPreviewFactory<T extends Document> {
    PreviewModel create(String path);

     static DocumentPreviewFactory<? extends Document> getInstance(String path) {
        if(path.endsWith(".docx")) {
            return new WordPreviewFactory();
        } else if(path.endsWith(".pdf")) {
            return new PDFPreviewFactory();
        }
        throw new RuntimeException("Not implemented");
    }
}
