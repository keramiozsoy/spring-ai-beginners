package com.example.SpringAIDemo.multimodality.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaMultimodalityConfig {

    @Bean("ollamaMultimodalityChatClient")
    public ChatClient ollamaMultimodalityChatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}

