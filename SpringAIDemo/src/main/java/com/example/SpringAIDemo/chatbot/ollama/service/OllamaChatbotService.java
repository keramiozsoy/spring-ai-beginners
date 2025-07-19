package com.example.SpringAIDemo.chatbot.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OllamaChatbotService {

    private final ChatClient chatClient;

    OllamaChatbotService(@Qualifier("ollamaChatClient") ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public ChatResponse chatResponse(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .chatResponse();
    }

    public Flux<String> chatStream(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .stream()
                .content();
    }
}
