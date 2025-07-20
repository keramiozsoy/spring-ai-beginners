package com.example.SpringAIDemo.chatbot.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OpenAIChatbotService {
    private final ChatClient chatClient;

    OpenAIChatbotService(@Qualifier("openAiChatClient") ChatClient openAiChatClient) {
        this.chatClient = openAiChatClient;
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
