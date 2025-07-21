package com.example.SpringAIDemo.multimodality.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaMultimodalityConfig {

    @Bean(name = "baseUrl")
    @ConditionalOnProperty(prefix = "spring.ai.ollama", name = "base-url")
    public String baseUrl(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return baseUrl;
    }

    @Bean("ollamaApi")
    public OllamaApi ollamaApi(@Qualifier(value = "baseUrl") String baseUrl){
        return OllamaApi.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean("ollamaChatModel")
    public OllamaChatModel ollamaChatModel(@Qualifier(value = "ollamaApi") OllamaApi ollamaApi){
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(
                        OllamaOptions.builder()
//                                .model(OllamaModel.LLAVA)
                                .model("llava-phi3")
                                .build()
                ).build();

    }

    @Bean("ollamaMultimodalityChatClient")
    public ChatClient ollamaMultimodalityChatClient(@Qualifier(value = "ollamaChatModel") OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

}

