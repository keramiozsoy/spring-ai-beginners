package com.example.SpringAIDemo.rag.ollama.api;

import com.example.SpringAIDemo.rag.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.rag.ollama.service.OllamaChatbotService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ollama/rag/postgres")
public class OllamaRagPostgresController {

    private final OllamaChatbotService ollamaChatbotService;

    OllamaRagPostgresController(OllamaChatbotService ollamaChatbotService) {
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
