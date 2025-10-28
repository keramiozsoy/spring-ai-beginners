package com.example.SpringAIDemo.chatbot.ollamaRag.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class OllamaRagVectorDatabaseInitializer {

    private final VectorStore vectorStore;

    OllamaRagVectorDatabaseInitializer(@Qualifier("ollamaRagChatbotVectorStore") VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void init() {
        ClassPathResource classPathResource = new ClassPathResource("ollamaRag/rules/rule1.txt");
        var textReader = new TextReader(classPathResource);
        textReader.getCustomMetadata().put("filename", "rule1");
        textReader.setCharset(Charset.defaultCharset());
        List<Document> documents = new ArrayList<>(textReader.get());

        var textSplitter = new TokenTextSplitter();
        var transformedDocuments = textSplitter.apply(documents);

        vectorStore.add(transformedDocuments);
    }
}
