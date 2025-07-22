package com.example.SpringAIDemo.chatbot.ollamaRag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaRagChatbotService {

    private final ChatClient chatClient;

    OllamaRagChatbotService(@Qualifier("ollamaRagChatbotChatClient")  ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }
}
