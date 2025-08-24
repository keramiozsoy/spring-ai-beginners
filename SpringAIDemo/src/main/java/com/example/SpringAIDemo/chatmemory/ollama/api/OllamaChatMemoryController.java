package com.example.SpringAIDemo.chatmemory.ollama.api;

import com.example.SpringAIDemo.chatmemory.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.chatmemory.ollama.service.OllamaChatMemoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama/chat/memory")
public class OllamaChatMemoryController {

    private final OllamaChatMemoryService ollamaChatMemoryService;

    OllamaChatMemoryController(OllamaChatMemoryService ollamaChatMemoryService) {
        this.ollamaChatMemoryService = ollamaChatMemoryService;
    }


    // Give info and use /ask endpoint to get information
    @GetMapping("/1/info")
    public String shareInfo(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE_HI) String userMessage) {
        return ollamaChatMemoryService.sentMessage(userMessage);
    }

    @GetMapping("/2/ask")
    public String askAboutInfo(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE_QUESTION) String userMessage) {
        return ollamaChatMemoryService.sentMessage(userMessage);
    }
}
