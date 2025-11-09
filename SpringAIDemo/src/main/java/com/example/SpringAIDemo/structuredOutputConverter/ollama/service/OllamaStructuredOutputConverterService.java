package com.example.SpringAIDemo.structuredOutputConverter.ollama.service;

import com.example.SpringAIDemo.structuredOutputConverter.ollama.dto.ActorsFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaStructuredOutputConverterService {


    private final ChatClient chatClient;

    OllamaStructuredOutputConverterService(@Qualifier("ollamaStructuredOutputConverterChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    public String content(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .content();
    }

    public ChatResponse chatResponse(String userMessage) {
        // org.springframework.ai.audio.transcription.AudioTranscriptionResponse
        // org.springframework.ai.chat.model.ChatResponse
        // org.springframework.ai.embedding.EmbeddingResponse
        // org.springframework.ai.image.ImageResponse
        // org.springframework.ai.moderation.ModerationResponse
        // org.springframework.ai.openai.audio.speech.Speech

        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .chatResponse();
    }

    public ChatClientResponse chatClientResponse(String userMessage) {
        return chatClient
                .prompt()
                .user(userMessage)
                .call()
                .chatClientResponse();
    }

    public ActorsFilms structuredOutputFormatter(String userMessage, String paramKey, String paramValue) {
        ResponseEntity<ChatResponse, ActorsFilms> responseEntity =
                chatClient
                        .prompt()
                        .user(u -> {
                            u.text(userMessage);
                            u.param(paramKey, paramValue);
                        })
                        .call()
                        .responseEntity(ActorsFilms.class);

        return responseEntity.entity();
    }


}
