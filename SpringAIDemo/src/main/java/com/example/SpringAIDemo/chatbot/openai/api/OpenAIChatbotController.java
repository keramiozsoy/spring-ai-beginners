package com.example.SpringAIDemo.chatbot.openai.api;

import com.example.SpringAIDemo.chatbot.openai.service.OpenAIChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/openai/chatbot")
public class OpenAIChatbotController {

    private final OpenAIChatbotService openAIChatbotService;

    OpenAIChatbotController(OpenAIChatbotService openAIChatbotService) {
        this.openAIChatbotService = openAIChatbotService;
    }

    // Blocking-Request
    @GetMapping("/chat/{userMessage}")
    public String chat(@PathVariable String userMessage) {
        return openAIChatbotService.chat(userMessage);
    }

    // Non-Blocking-Request ( long running request )
    @GetMapping("/chat/stream/{userMessage}")
    public Flux<String> chatStream(@PathVariable String userMessage) {
        return openAIChatbotService.chatStream(userMessage);
    }
}
