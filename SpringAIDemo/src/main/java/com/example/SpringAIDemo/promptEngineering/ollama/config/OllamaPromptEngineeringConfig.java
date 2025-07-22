package com.example.SpringAIDemo.promptEngineering.ollama.config;

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
public class OllamaPromptEngineeringConfig {

    @Bean(name = "ollamaPromptEngineeringBaseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }

    @Bean("ollamaPromptEngineeringOllamaApi")
    public OllamaApi ollamaApi(@Qualifier("ollamaPromptEngineeringBaseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaPromptEngineeringOllamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier("ollamaPromptEngineeringOllamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
                                .model(OllamaModel.LLAMA3)
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                                .build())
                .build();

    }

    @Bean("ollamaPromptEngineeringOllamaChatClient")
    public ChatClient ollamaChatClient(@Qualifier("ollamaPromptEngineeringOllamaChatModel") OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}
