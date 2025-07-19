package com.example.SpringAIDemo.chatbot.ollama.api;

import com.example.SpringAIDemo.chatbot.ollama.service.OllamaChatbotService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/ollama/chatbot")
public class OllamaChatbotController {

    private final OllamaChatbotService ollamaChatbotService;

    OllamaChatbotController(OllamaChatbotService ollamaChatbotService) {
        this.ollamaChatbotService = ollamaChatbotService;
    }

    // Blocking-Request
    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return ollamaChatbotService.chat(userMessage);
    }

    @GetMapping("/chat/chatResponse/{userMessage}")
    public ChatResponse chatResponse(@PathVariable String userMessage) {
        // TODO : add default userMessage to show
        return ollamaChatbotService.chatResponse(userMessage);
    }

    // Non-Blocking-Request ( long running request )
    @GetMapping("/chat/stream/{userMessage}")
    public Flux<String> chatStream(@PathVariable String userMessage) {
        return ollamaChatbotService.chatStream(userMessage);
    }
}
