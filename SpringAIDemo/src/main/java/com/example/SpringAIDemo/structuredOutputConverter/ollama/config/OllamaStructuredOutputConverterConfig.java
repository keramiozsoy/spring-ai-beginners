package com.example.SpringAIDemo.structuredOutputConverter.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.ai.ollama.management.PullModelStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaStructuredOutputConverterConfig {

    @Bean(name = "ollamaStructuredOutputConverterBaseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }


    @Bean("ollamaStructuredOutputConverterOllamaApi")
    public OllamaApi ollamaApi(@Qualifier("ollamaStructuredOutputConverterBaseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaStructuredOutputConverterOllamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier("ollamaStructuredOutputConverterOllamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
                                .model(OllamaModel.LLAMA3)
//                                .format("json")
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                                .build())
                .build();

    }

    @Bean("ollamaStructuredOutputConverterChatClient")
    public ChatClient ollamaStructuredOutputConverter(@Qualifier("ollamaStructuredOutputConverterOllamaChatModel") OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }


}
