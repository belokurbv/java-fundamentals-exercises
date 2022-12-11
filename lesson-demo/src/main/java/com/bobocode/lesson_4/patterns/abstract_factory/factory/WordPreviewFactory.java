package com.bobocode.lesson_4.patterns.abstract_factory.factory;

import com.bobocode.lesson_4.patterns.abstract_factory.PreviewModel;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.PDFDocument;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.WordDocument;
import com.bobocode.lesson_4.patterns.abstract_factory.metadata.MSOfficeDocumentMetadataProvider;
import com.bobocode.lesson_4.patterns.abstract_factory.metadata.MetadataProvider;
import com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer.DOCXThumbnailer;
import com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer.Thumbnailer;

public class WordPreviewFactory implements DocumentPreviewFactory<PDFDocument> {
    private final Thumbnailer<WordDocument> thumbnailer;

    private final MetadataProvider<WordDocument> metadataProvider;

    public WordPreviewFactory() {
        this.metadataProvider = new MSOfficeDocumentMetadataProvider();
        this.thumbnailer = new DOCXThumbnailer();
    }

    @Override
    public PreviewModel create(String path) {
        var doc = new WordDocument(path);
        return new PreviewModel(metadataProvider.getKeywords(doc), thumbnailer.generate(doc));
    }
}
