package com.example.SpringAIDemo.chatbot.anthropic.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AnthropicChatbotService {
    private final ChatClient chatClient;

    AnthropicChatbotService(@Qualifier("anthropicChatClient") ChatClient anthropicChatClient){
        this.chatClient = anthropicChatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public Flux<String> chatStream(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .stream()
                .content();
    }
}
