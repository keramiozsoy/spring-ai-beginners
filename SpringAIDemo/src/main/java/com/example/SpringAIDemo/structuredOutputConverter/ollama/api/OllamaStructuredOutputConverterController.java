package com.example.SpringAIDemo.structuredOutputConverter.ollama.api;

import com.example.SpringAIDemo.structuredOutputConverter.ollama.dto.ActorsFilms;
import com.example.SpringAIDemo.structuredOutputConverter.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.structuredOutputConverter.ollama.service.OllamaStructuredOutputConverterService;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/ollama/structuredOutputConverter")
public class OllamaStructuredOutputConverterController {

    private final OllamaStructuredOutputConverterService ollamaStructuredOutputConverterService;

    OllamaStructuredOutputConverterController(OllamaStructuredOutputConverterService ollamaStructuredOutputConverterService) {
        this.ollamaStructuredOutputConverterService = ollamaStructuredOutputConverterService;
    }

    @GetMapping("/1/chat/content")
    public String content(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE) String userMessage) {
        return ollamaStructuredOutputConverterService.content(userMessage);
    }

    @GetMapping("/2/chat/chatResponse")
    public ChatResponse chatResponse(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE) String userMessage) {
        return ollamaStructuredOutputConverterService.chatResponse(userMessage);
    }

    @GetMapping("/3/chat/chatClientResponse")
    public ChatClientResponse chatClientResponse(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE) String userMessage) {
        return ollamaStructuredOutputConverterService.chatClientResponse(userMessage);
    }


    @GetMapping("/4/chat/structuredOutputFormatter")
    public ActorsFilms structuredOutputFormatter(
            @RequestParam(defaultValue = CustomPrompt.Structured.PromptUserSpec.USER_MESSAGE) String userMessage,
            @RequestParam(defaultValue = CustomPrompt.Structured.PromptUserSpec.USER_MESSAGE_PARAM_KEY) String paramKey,
            @RequestParam(defaultValue = CustomPrompt.Structured.PromptUserSpec.USER_MESSAGE_PARAM_VALUE) String paramValue) {
        return ollamaStructuredOutputConverterService.structuredOutputFormatter(userMessage, paramKey, paramValue);
    }
}
