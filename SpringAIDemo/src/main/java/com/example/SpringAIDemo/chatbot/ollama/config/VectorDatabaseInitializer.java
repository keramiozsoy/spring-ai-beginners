package com.example.SpringAIDemo.chatbot.ollama.config;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class VectorDatabaseInitializer {

    private final VectorStore vectorStore;

    VectorDatabaseInitializer(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void init() {
        ClassPathResource classPathResource = new ClassPathResource("rules/rule1.txt");
        var textReader = new TextReader(classPathResource);
        textReader.getCustomMetadata().put("filename", "rule1");
        textReader.setCharset(Charset.defaultCharset());
        List<Document> documents = new ArrayList<>(textReader.get());

        var textSplitter = new TokenTextSplitter();
        var transformedDocuments = textSplitter.apply(documents);

        vectorStore.add(transformedDocuments);
    }
}
