package com.example.SpringAIDemo.chatmemory.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.ai.ollama.management.PullModelStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaChatMemoryConfig {

    @Bean(name = "ollamaChatMemoryBaseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }

    @Bean("ollamaChatMemoryOllamaApi")
    public OllamaApi ollamaApi(@Qualifier("ollamaChatMemoryBaseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaChatMemoryOllamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier("ollamaChatMemoryOllamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
                                .model("qwen3:0.6b")
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                                .build())
                .build();

    }

    @Bean("ollamaChatMemoryOllamaChatMemory")
    public MessageWindowChatMemory ollamaChatMemory(){
        return MessageWindowChatMemory
                .builder()
                //.chatMemoryRepository()
                //.maxMessages()
                .build();
    }


    @Bean("ollamaMessageChatMemoryAdvisor")
    public MessageChatMemoryAdvisor memoryAdvisor(@Qualifier("ollamaChatMemoryOllamaChatMemory") ChatMemory chatMemory){
        return MessageChatMemoryAdvisor
                .builder(chatMemory)
                //.conversationId()
                //.order()
                //.scheduler()
                .build();
    }


    @Bean("ollamaChatMemoryChatClient")
    public ChatClient ollamaChatClient(@Qualifier("ollamaChatMemoryOllamaChatModel") OllamaChatModel chatModel,
                                       @Qualifier("ollamaMessageChatMemoryAdvisor") MessageChatMemoryAdvisor messageChatMemoryAdvisor) {
        return ChatClient.builder(chatModel)
                .defaultAdvisors(messageChatMemoryAdvisor)
                .build();
//        https://docs.spring.io/spring-ai/reference/api/advisors.html
    }

}

