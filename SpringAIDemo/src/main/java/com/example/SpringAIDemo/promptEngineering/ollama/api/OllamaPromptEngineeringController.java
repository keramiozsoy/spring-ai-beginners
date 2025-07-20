package com.example.SpringAIDemo.promptEngineering.ollama.api;

import com.example.SpringAIDemo.promptEngineering.ollama.prompt.CustomPrompt;
import com.example.SpringAIDemo.promptEngineering.ollama.service.OllamaPromptEngineeringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/ollama/promptEngineering")
public class OllamaPromptEngineeringController {

    private final OllamaPromptEngineeringService ollamaPromptEngineeringService;

    OllamaPromptEngineeringController(OllamaPromptEngineeringService ollamaPromptEngineeringService) {
        this.ollamaPromptEngineeringService = ollamaPromptEngineeringService;
    }

    @GetMapping("/1/chat/userMessageNotInScope")
    public String chatUserMessageNotInScope(
            @RequestParam(defaultValue = CustomPrompt.UserMessageOutOfScope.SYSTEM_MESSAGE_CHILD_FOR_MATH) String systemMessage,
            @RequestParam(defaultValue = CustomPrompt.UserMessageOutOfScope.USER_MESSAGE_NOT_IN_SCOPE) String userMessage) {
        return ollamaPromptEngineeringService.chatUserMessageNotInScope(systemMessage, userMessage);
    }

    @GetMapping("/2/chat/promptUserSpecParamKey")
    public String chatPromptUserSpec(
            @RequestParam(defaultValue = CustomPrompt.PromptUserSpec.SYSTEM_MESSAGE) String systemMessage,
            @RequestParam(defaultValue = CustomPrompt.PromptUserSpec.USER_MESSAGE) String userMessage,
            @RequestParam(defaultValue = CustomPrompt.PromptUserSpec.USER_MESSAGE_PARAM_KEY) String paramKey,
            @RequestParam(defaultValue = CustomPrompt.PromptUserSpec.USER_MESSAGE_PARAM_VALUE) String paramValue
    ) {
        return ollamaPromptEngineeringService.promptUserSpecParamKey(systemMessage, userMessage, paramKey, paramValue);
    }

}
