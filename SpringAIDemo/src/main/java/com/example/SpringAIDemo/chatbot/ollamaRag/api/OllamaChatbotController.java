package com.example.SpringAIDemo.chatbot.ollamaRag.api;

import com.example.SpringAIDemo.chatbot.ollamaRag.service.OllamaChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public
@RequestMapping("/ollama/chatbot") class OllamaChatbotController {

    private final OllamaChatbotService ollamaChatbotService;

    OllamaChatbotController(OllamaChatbotService ollamaChatbotService) {
        this.ollamaChatbotService = ollamaChatbotService;
    }

    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return ollamaChatbotService.chat(userMessage);
    }
}
