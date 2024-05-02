package com.ai.rag.services.impl;

import com.ai.rag.services.TextEmbeddingService;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ai.document.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextEmbeddingServiceImpl implements TextEmbeddingService {

    private final VectorStore vectorStore;

    public TextEmbeddingServiceImpl(VectorStore vectorStore, JdbcTemplate jdbcTemplate) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void textEmbeddingPdf(MultipartFile[] pdfFiles) {
        List<Resource> resources = Arrays.stream(pdfFiles)
                .map(file -> {
                    try {
                        return new ByteArrayResource(file.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        // Call another method to process resources or do the necessary logic here
        processResources(resources.toArray(new Resource[0]));
    }

    private void processResources(Resource[] resources) {
        PdfDocumentReaderConfig config = PdfDocumentReaderConfig.defaultConfig();
        String content = "";
        for (Resource resource : resources) {
            PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(resource, config);
            List<Document> documentList = pagePdfDocumentReader.get();
            content += documentList.stream().map(d -> d.getContent()).collect(Collectors.joining("\n")) + "\n";
        }

        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<String> chunks = tokenTextSplitter.split(content, 1000);
        List<Document> chunksDocs = chunks.stream().map(chunk -> new Document(chunk)).collect(Collectors.toList());
        vectorStore.accept(chunksDocs);
    }
}
