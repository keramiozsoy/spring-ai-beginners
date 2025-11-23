package com.example.SpringAIDemo.chatbot.ollamaRag.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatbotRagService {

    private final ChatClient chatClient;

    OllamaChatbotRagService(@Qualifier("ollamaChatbotRagChatClient")  ChatClient chatClient) {
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
