package com.example.SpringAIDemo.tools.ollama.service;

import com.example.SpringAIDemo.tools.ollama.tool.date.DateTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaToolsService {

    private final ChatClient chatClient;

    OllamaToolsService(@Qualifier("ollamaToolsOllamaChatClient")  ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public String chatWithTools(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .tools(new DateTool())
                .call()
                .content();
    }
}
