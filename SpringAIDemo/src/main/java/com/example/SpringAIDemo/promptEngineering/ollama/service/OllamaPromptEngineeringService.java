package com.example.SpringAIDemo.promptEngineering.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaPromptEngineeringService {

    private final ChatClient chatClient;

    OllamaPromptEngineeringService(@Qualifier("ollamaPromptEngineeringOllamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatUserMessageNotInScope(String systemMessage, String userMessage) {
        return chatClient
                .prompt()
                .system(systemMessage)
                .user(userMessage)
                .call()
                .content();
    }

    public String promptUserSpecParamKey(String systemMessage, String userMessage, String paramKey, String paramValue) {
        return chatClient
                .prompt()
                .system(systemMessage)
                .user(u -> {
                    u.text(userMessage);
                    u.param(paramKey, paramValue);
                })
                .call()
                .content();
    }
}
