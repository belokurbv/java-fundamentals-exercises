package com.bobocode.lesson_4.patterns.abstract_factory.factory;

import com.bobocode.lesson_4.patterns.abstract_factory.PreviewModel;
import com.bobocode.lesson_4.patterns.abstract_factory.doc.PDFDocument;
import com.bobocode.lesson_4.patterns.abstract_factory.metadata.AdobeMetadataProvider;
import com.bobocode.lesson_4.patterns.abstract_factory.metadata.MSOfficeDocumentMetadataProvider;
import com.bobocode.lesson_4.patterns.abstract_factory.metadata.MetadataProvider;
import com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer.PDFThumbnailer;
import com.bobocode.lesson_4.patterns.abstract_factory.thumbnailer.Thumbnailer;

public class PDFPreviewFactory implements DocumentPreviewFactory<PDFDocument> {
    private final Thumbnailer<PDFDocument> thumbnailer;
    
    private final MetadataProvider<PDFDocument> metadataProvider;

    public PDFPreviewFactory() {
        this.metadataProvider = new AdobeMetadataProvider();
        this.thumbnailer = new PDFThumbnailer();
    }
    
    @Override
    public PreviewModel create(String path) {
        var doc = new PDFDocument(path);
        return new PreviewModel(metadataProvider.getKeywords(doc), thumbnailer.generate(doc));
    }
}
