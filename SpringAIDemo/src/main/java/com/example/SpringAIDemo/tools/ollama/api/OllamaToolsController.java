package com.example.SpringAIDemo.tools.ollama.api;

import com.example.SpringAIDemo.tools.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.tools.ollama.service.OllamaToolsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama/tools")
public class OllamaToolsController {

    private final OllamaToolsService ollamaToolsService;

    OllamaToolsController(OllamaToolsService ollamaToolsService) {
        this.ollamaToolsService = ollamaToolsService;
    }

    @GetMapping("/example1/chat/chatWithoutTools")
    public String chatWithoutTools(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_ZONE_AND_TOMORROW) String userMessage) {
        return ollamaToolsService.chat(userMessage);
    }

    @GetMapping("/example2/chat/chatWithTools")
    public String chatWithTools(
            @RequestParam(defaultValue = CustomPrompt.UnStructured.ASK_ZONE_AND_TOMORROW) String userMessage) {
        return ollamaToolsService.chatWithTools(userMessage);
    }

}
