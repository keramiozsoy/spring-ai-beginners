package com.example.SpringAIDemo.promptEngineering.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaPromptEngineeringService {

    private final ChatClient chatClient;

    OllamaPromptEngineeringService(@Qualifier("ollamaPromptEngineeringOllamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chatUserMessageScope(String systemMessage, String userMessage) {
        return chatClient
                .prompt()
                .system(systemMessage)
                .user(userMessage)
                .call()
                .content();
    }


    public String chatUserMessageScopePromptType(String systemMessage, String userMessage) {

        // org.springframework.ai.chat.messages.MessageType

        UserMessage userMessage1 = new UserMessage(userMessage);
        SystemMessage systemMessage1 = new SystemMessage(systemMessage);
        // AssistantMessage assistant1 = new AssistantMessage();
        // ToolResponseMessage toolResponseMessage = new ToolResponseMessage();

        return chatClient
                .prompt(Prompt.builder()
                        .messages(List.of(userMessage1, systemMessage1))
                        .build())
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
