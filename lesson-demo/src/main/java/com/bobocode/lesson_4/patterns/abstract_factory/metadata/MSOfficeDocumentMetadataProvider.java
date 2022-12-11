package com.bobocode.lesson_4.patterns.abstract_factory.metadata;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.Document;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.WordDocument;

import java.util.List;

public class MSOfficeDocumentMetadataProvider implements MetadataProvider<WordDocument> {
    @Override
    public List<String> getKeywords(WordDocument wordDocument) {
        return List.of("Keyword", "lorem");
    }
}
