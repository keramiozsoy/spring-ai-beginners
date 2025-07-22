package com.example.SpringAIDemo.chatbot.ollamaRag.api;

import com.example.SpringAIDemo.chatbot.ollamaRag.service.OllamaRagChatbotService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama/rag/chatbot")
public class OllamaRagChatbotController {

    private final OllamaRagChatbotService ollamaRagChatbotService;

    OllamaRagChatbotController(OllamaRagChatbotService ollamaRagChatbotService) {
        this.ollamaRagChatbotService = ollamaRagChatbotService;
    }

    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return ollamaRagChatbotService.chat(userMessage);
    }
}
