package com.example.SpringAIDemo.multimodality.ollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class OllamaMultimodalityService {

    private final ChatClient chatClient;
    @Value("classpath:/images/cars.png")
    private Resource image;

    OllamaMultimodalityService(@Qualifier("ollamaMultimodalityChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String imageToText() {
        return chatClient
                .prompt()
                .user(u -> {
                    u.text("Could you please describe what you see in the following image");
                    u.media(MimeTypeUtils.IMAGE_PNG, image);
                })
                .call()
                .content();
    }
}
