package com.bobocode.lesson_4.patterns.abstract_factory.metadata;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.PDFDocument;

import java.util.List;

public class AdobeMetadataProvider implements MetadataProvider<PDFDocument> {
    @Override
    public List<String> getKeywords(PDFDocument document) {
        return List.of(document.getName().split(","));
    }
}
