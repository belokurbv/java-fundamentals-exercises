package com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.PDFDocument;

public class PDFThumbnailer implements Thumbnailer<PDFDocument> {
    @Override
    public byte[] generate(PDFDocument document) {
        return document.getContent();
    }
}
