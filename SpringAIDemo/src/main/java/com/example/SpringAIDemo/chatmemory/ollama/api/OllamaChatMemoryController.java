package com.example.SpringAIDemo.chatmemory.ollama.api;

import com.example.SpringAIDemo.chatmemory.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.chatmemory.ollama.service.OllamaChatMemoryService;
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

    @GetMapping("/example1/askBeforeInfo")
    public String askBeforeInfo(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE_QUESTION) String userMessage) {
        return ollamaChatMemoryService.sentMessage(userMessage);
    }


    // Share info and use /ask endpoint to get information
    @GetMapping("/example2/shareInfo")
    public String shareInfo(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE_HI) String userMessage) {
        return ollamaChatMemoryService.sentMessage(userMessage);
    }

    @GetMapping("/example3/askAboutInfo")
    public String askAboutInfo(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.USER_MESSAGE_QUESTION) String userMessage) {
        return ollamaChatMemoryService.sentMessage(userMessage);
    }
}
