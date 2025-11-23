package com.example.SpringAIDemo.chatbot.ollamaRag.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class OllamaChatbotRagVectorDatabaseInitializer {

    private final SimpleVectorStore vectorStore;

    @Value("ollamaChatbotRag/rules/rule1.txt")
    private ClassPathResource resource;

    OllamaChatbotRagVectorDatabaseInitializer(@Qualifier("ollamaChatbotRagVectorStore") SimpleVectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    void init() throws IOException {
        File vectorFile = getLocalVectorFile();

        if (vectorFile.exists()) {
            // load existing vector file.
            vectorStore.load(vectorFile);
        } else {
            var textReader = new TextReader(resource);
            textReader.getCustomMetadata().put("filename", "rule1");
            textReader.setCharset(Charset.defaultCharset());
            List<Document> documents = textReader.get();

            var textSplitter = new TokenTextSplitter();
            var transformedDocuments = textSplitter.apply(documents);

            vectorStore.add(transformedDocuments);

            // Create local vector file to prevent access db all times.
            vectorStore.save(vectorFile);
        }
    }


    private File getLocalVectorFile(){
        Path path = Paths.get("src", "main","resources", "ollamaChatbotRag","vectorLocal");
        String absolute = path.toFile().getAbsolutePath() + File.separator + "simpleVector.txt";
        return new File(absolute);
    }


}
