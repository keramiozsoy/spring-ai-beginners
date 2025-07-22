package com.example.SpringAIDemo.multimodality.ollama.config;

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
public class OllamaMultimodalityConfig {

    @Bean(name = "ollamaMultimodalityBaseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }

    @Bean("ollamaMultimodalityOllamaApi")
    public OllamaApi ollamaApi(@Qualifier("ollamaMultimodalityBaseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaMultimodalityOllamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier("ollamaMultimodalityOllamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
                                .model(OllamaModel.LLAVA)
                                .build()
                )
                .modelManagementOptions(
                        ModelManagementOptions.builder()
                                .pullModelStrategy(PullModelStrategy.WHEN_MISSING)
                                .build())
                .build();

    }

    @Bean("ollamaMultimodalityChatClient")
    public ChatClient ollamaMultimodalityChatClient(@Qualifier("ollamaMultimodalityOllamaChatModel") OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}

