package com.example.SpringAIDemo.chatbot.openai.api;

import com.example.SpringAIDemo.chatbot.openai.service.OpenAIChatbotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai/chatbot")
public class OpenAIChatbotController {

    private final OpenAIChatbotService openAIChatbotService;

    OpenAIChatbotController(OpenAIChatbotService openAIChatbotService) {
        this.openAIChatbotService = openAIChatbotService;
    }

    @GetMapping("/chat/{chatId}/{userMessage}")
    public String chat(@PathVariable String chatId, @PathVariable String userMessage) {
        return openAIChatbotService.chat(chatId, userMessage);
    }
}
