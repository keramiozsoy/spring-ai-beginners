package com.example.SpringAIDemo.chatbot.ollamaRag.api;

import com.example.SpringAIDemo.chatbot.ollamaRag.service.OllamaRagChatbotService;
import com.example.SpringAIDemo.chatbot.ollamaRag.prompt.CustomPrompt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ollama/rag/chatbot")
public class OllamaRagChatbotController {

    private final OllamaRagChatbotService ollamaRagChatbotService;

    OllamaRagChatbotController(OllamaRagChatbotService ollamaRagChatbotService) {
        this.ollamaRagChatbotService = ollamaRagChatbotService;
    }

    @GetMapping("/1/chat/outofcontext")
    public String chatOutOfContext(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_HELLO) String userMessage) {
        return ollamaRagChatbotService.chat(userMessage);
    }

    @GetMapping("/2/chat/incontext")
    public String chatInContext(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_HUMAN_RESOURCES_DEPARTMENT) String userMessage) {
        return ollamaRagChatbotService.chat(userMessage);
    }

}
