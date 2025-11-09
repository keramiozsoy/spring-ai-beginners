package com.example.SpringAIDemo.chatbot.ollamaRag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.ai.ollama.management.PullModelStrategy;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class OllamaRagChatbotConfig {

    @Bean(name = "ollamaRagChatbotBaseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }

    @Bean("ollamaRagChatbotOllamaApi")
    public OllamaApi ollamaApi(@Qualifier("ollamaRagChatbotBaseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaRagChatbotOllamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier("ollamaRagChatbotOllamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
//                                .model(OllamaModel.LLAMA3)
                                .model("qwen3:0.6b")
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                                .build())
                .build();

    }


    @Bean("ollamaRagChatbotEmbeddingModel")
    public OllamaEmbeddingModel embeddingModel(@Qualifier("ollamaRagChatbotOllamaApi") OllamaApi ollamaApi) {
        return OllamaEmbeddingModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
                                .model(OllamaModel.MXBAI_EMBED_LARGE) // To store sentences/words - BERT architecture
//                                .model(OllamaModel.NOMIC_EMBED_TEXT)
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                        .build())
                .build();
    }

    @Bean("ollamaRagChatbotVectorStore")
    public SimpleVectorStore vectorStore(@Qualifier("ollamaRagChatbotEmbeddingModel") EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean("ollamaRagChatbotRagAdvisor")
    public RetrievalAugmentationAdvisor ragAdvisor(@Qualifier("ollamaRagChatbotVectorStore") VectorStore vectorStore) {
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(VectorStoreDocumentRetriever.builder()
                        .similarityThreshold(0.50)
                        .vectorStore(vectorStore)
                        .build())
                .build();
    }


    @Bean("ollamaRagChatbotChatClient")
    public ChatClient ollamaChatClient(@Qualifier("ollamaRagChatbotOllamaChatModel") OllamaChatModel chatModel,
                                       @Qualifier("ollamaRagChatbotRagAdvisor") RetrievalAugmentationAdvisor ragAdvisor) {


        ClassPathResource systemPrompt = new ClassPathResource("ollamaRag/prompts/systemPrompt1.txt");

        return ChatClient.builder(chatModel)
                .defaultAdvisors(ragAdvisor)
                .defaultSystem(systemPrompt)
                .build();
    }

}
