package com.example.SpringAIDemo.chatbot.anthropic.api;

import com.example.SpringAIDemo.chatbot.anthropic.service.AnthropicChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/anthropic/chatbot")
public class AnthropicChatbotController {

    private final AnthropicChatbotService anthropicChatbotService;

    AnthropicChatbotController(AnthropicChatbotService anthropicChatbotService) {
        this.anthropicChatbotService = anthropicChatbotService;
    }

    // Blocking-Request
    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return anthropicChatbotService.chat(userMessage);

    }

    // Non-Blocking-Request ( long running request )
    @GetMapping("/chat/stream/{userMessage}")
    public Flux<String> chatStream(@PathVariable String userMessage) {
        return anthropicChatbotService.chatStream(userMessage);
    }
}
