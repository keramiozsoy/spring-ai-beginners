package com.example.SpringAIDemo.promptEngineering.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaPromptEngineeringConfig {

    @Bean("ollamaPromptEngineeringChatClient")
    public ChatClient ollamaPromptEngineeringChatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}
