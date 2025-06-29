package com.example.SpringAIDemo.chatbot.ollama.api;

import com.example.SpringAIDemo.chatbot.ollama.service.OllamaChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public
@RequestMapping("/v1/ollama/chatbot") class OllamaChatbotController {

    private final OllamaChatbotService ollamaChatbotService;

    OllamaChatbotController(OllamaChatbotService ollamaChatbotService) {
        this.ollamaChatbotService = ollamaChatbotService;
    }

    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return ollamaChatbotService.chat(userMessage);
    }
}
