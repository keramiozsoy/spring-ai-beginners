package com.example.SpringAIDemo.structuredOutputConverter.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaStructuredOutputConverterConfig {

    @Bean("OllamaStructuredOutputConverterChatClient")
    public ChatClient ollamaStructuredOutputConverter(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}
