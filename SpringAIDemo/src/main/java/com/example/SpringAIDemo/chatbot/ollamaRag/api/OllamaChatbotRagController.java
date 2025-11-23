package com.example.SpringAIDemo.chatbot.ollamaRag.api;

import com.example.SpringAIDemo.chatbot.ollamaRag.service.OllamaChatbotRagService;
import com.example.SpringAIDemo.chatbot.ollamaRag.prompt.CustomPrompt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ollama/chatbot/rag")
public class OllamaChatbotRagController {

    private final OllamaChatbotRagService ollamaChatbotRagService;

    OllamaChatbotRagController(OllamaChatbotRagService ollamaChatbotRagService) {
        this.ollamaChatbotRagService = ollamaChatbotRagService;
    }

    @GetMapping("/example1/chat/outOfContext")
    public String chatOutOfContext(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_HELLO) String userMessage) {
        return ollamaChatbotRagService.chat(userMessage);
    }

    @GetMapping("/example2/chat/inContextNotPermitted")
    public String inContextNotPermitted(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_RESEARCH_DEPARTMENT) String userMessage) {
        return ollamaChatbotRagService.chat(userMessage);
    }

    @GetMapping("/example3/chat/inContext")
    public String chatInContext(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_HUMAN_RESOURCES_DEPARTMENT) String userMessage) {
        return ollamaChatbotRagService.chat(userMessage);
    }

}
