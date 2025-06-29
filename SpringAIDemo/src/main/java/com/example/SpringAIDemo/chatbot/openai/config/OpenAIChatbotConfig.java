package com.example.SpringAIDemo.chatbot.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIChatbotConfig {
//    @Bean
//    ChatMemory chatMemory() {
//        return new InMemoryChatMemory();
//    }

    @Bean("openAiChatClient")
    public ChatClient openAiChatClient(@Qualifier("openAiChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}
