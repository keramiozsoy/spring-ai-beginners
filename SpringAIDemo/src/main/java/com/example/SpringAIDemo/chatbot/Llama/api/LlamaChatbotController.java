package com.example.SpringAIDemo.chatbot.Llama.api;

import com.example.SpringAIDemo.chatbot.Llama.service.LlamaChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/llama/employee")
public class LlamaChatbotController {

    private final LlamaChatbotService llamaChatbotService;

    LlamaChatbotController(LlamaChatbotService llamaChatbotService) {
        this.llamaChatbotService = llamaChatbotService;
    }

    @GetMapping("/chat/{chatId}/{userMessage}")
    public String chat(@PathVariable String chatId, @PathVariable String userMessage) {
        return llamaChatbotService.chat(chatId, userMessage);
    }
}
