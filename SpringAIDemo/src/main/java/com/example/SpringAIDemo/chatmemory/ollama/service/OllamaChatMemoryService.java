package com.example.SpringAIDemo.chatmemory.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatMemoryService {

    private final ChatClient chatClient;


    OllamaChatMemoryService(@Qualifier("ollamaChatMemoryChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String sentMessage(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
