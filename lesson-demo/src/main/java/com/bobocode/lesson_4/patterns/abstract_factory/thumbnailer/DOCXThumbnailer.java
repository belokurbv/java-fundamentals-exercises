package com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer;

import com.bobocode.lesson_4.patterns.abstract_factory.doc.PDFDocument;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.WordDocument;

public class DOCXThumbnailer implements Thumbnailer<WordDocument> {
    @Override
    public byte[] generate(WordDocument document) {
        return document.getContent();
    }
}
