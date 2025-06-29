package com.example.SpringAIDemo.chatbot.ollamaRag.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class OllamaRagChatbotConfig {

    @Bean("ollamaRagChatClient")
    public ChatClient ollamaRagChatClient(OllamaChatModel chatModel, RetrievalAugmentationAdvisor ragAdvisor) {
        ClassPathResource systemPrompt = new ClassPathResource("prompts/prompt1.txt");

        return ChatClient.builder(chatModel)
                .defaultAdvisors(ragAdvisor)
                .defaultSystem(systemPrompt)
                .build();
    }

}
