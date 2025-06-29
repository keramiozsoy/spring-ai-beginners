package com.example.SpringAIDemo.chatbot.openai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OpenAIChatbotService {
    private final ChatClient chatClient;

    OpenAIChatbotService(@Qualifier("openAiChatClient") ChatClient openAiChatClient){
        this.chatClient = openAiChatClient;
    }

    public String chat(String chatId, String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
//                .advisors(a -> a
//                        .param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
//                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 50))
                .call()
                .content();
    }
}
