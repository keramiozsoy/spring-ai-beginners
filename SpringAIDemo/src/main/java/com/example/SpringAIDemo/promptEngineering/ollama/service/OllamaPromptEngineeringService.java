package com.example.SpringAIDemo.promptEngineering.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class OllamaPromptEngineeringService {


    private final ChatClient chatClient;

    OllamaPromptEngineeringService(@Qualifier("ollamaPromptEngineeringChatClient") ChatClient chatClient){
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
