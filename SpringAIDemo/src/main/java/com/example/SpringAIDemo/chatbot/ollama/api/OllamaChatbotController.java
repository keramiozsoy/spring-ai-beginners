package com.example.SpringAIDemo.chatbot.ollama.api;

import com.example.SpringAIDemo.chatbot.ollama.service.OllamaChatbotService;
import org.springframework.ai.chat.model.ChatResponse;
import com.example.SpringAIDemo.chatbot.ollama.prompt.CustomPrompt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ollama/chatbot")
public class OllamaChatbotController {

    private final OllamaChatbotService ollamaChatbotService;

    OllamaChatbotController(OllamaChatbotService ollamaChatbotService) {
        this.ollamaChatbotService = ollamaChatbotService;
    }

    // Blocking-Request
    @GetMapping("/example1/chat")
    public String chat(
            @RequestParam(defaultValue = CustomPrompt.UserMessage.USER_HELLO) String userMessage) {
        return ollamaChatbotService.chat(userMessage);
    }

    // Non-Blocking-Request ( long running request )
    @GetMapping("/example2/chat/stream")
    public Flux<String> chatStream(
            @RequestParam(defaultValue = CustomPrompt.UserMessage.USER_HELLO) String userMessage) {
        return ollamaChatbotService.chatStream(userMessage);
    }

    @GetMapping("/example3/chat/chatResponse")
    public ChatResponse chatResponse(
            @RequestParam(defaultValue = CustomPrompt.UserMessage.USER_HELLO) String userMessage) {
        return ollamaChatbotService.chatResponse(userMessage);
    }

}
