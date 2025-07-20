package com.example.SpringAIDemo.structuredOutputConverter.ollama.service;

import com.example.SpringAIDemo.structuredOutputConverter.ollama.dto.ActorsFilms;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.ResponseEntity;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.template.TemplateRenderer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OllamaStructuredOutputConverterService {


    private final ChatClient chatClient;

    OllamaStructuredOutputConverterService(@Qualifier("OllamaStructuredOutputConverterChatClient") ChatClient chatClient){
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
