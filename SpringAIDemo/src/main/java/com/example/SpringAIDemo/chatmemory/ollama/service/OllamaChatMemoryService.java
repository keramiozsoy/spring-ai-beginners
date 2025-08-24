package com.example.SpringAIDemo.chatmemory.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

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
