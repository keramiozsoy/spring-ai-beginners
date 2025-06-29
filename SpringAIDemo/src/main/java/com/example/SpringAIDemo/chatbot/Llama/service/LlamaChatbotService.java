package com.example.SpringAIDemo.chatbot.Llama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class LlamaChatbotService {

    private final ChatClient chatClient;

    LlamaChatbotService(@Qualifier("ollamaChatClient") ChatClient chatClient){
        this.chatClient = chatClient;
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
