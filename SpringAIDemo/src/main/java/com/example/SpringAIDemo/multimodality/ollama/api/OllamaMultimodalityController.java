package com.example.SpringAIDemo.multimodality.ollama.api;

import com.example.SpringAIDemo.multimodality.ollama.service.OllamaMultimodalityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ollama/multimodality")
public class OllamaMultimodalityController {

    private final OllamaMultimodalityService ollamaMultimodalityService;

    OllamaMultimodalityController(OllamaMultimodalityService ollamaMultimodalityService) {
        this.ollamaMultimodalityService = ollamaMultimodalityService;
    }

    @GetMapping("/1/chat/imageToText")
    public String chatImageToText() {
        return ollamaMultimodalityService.imageToText();
    }
}
