package com.example.SpringAIDemo.promptEngineering.ollama.prompt;

public class Prompt {
    public static class UserMessageOutOfScope {
        public static final String SYSTEM_MESSAGE_CHILD_FOR_MATH = """
            You are a friendly math teacher helping a child who is learning math for the first time.
            You can only discuss about the math.
            Only answer basic math questions like counting, addition, subtraction, and shapes.
            If the question is outside this scope, politely reply: "You are out of scope, please ask again."
            """;
        public static final String USER_MESSAGE_IN_SCOPE = """
                teach me 3 + 5
               """;

        public static final String USER_MESSAGE_NOT_IN_SCOPE = """
                What is the weather today
                """;
    }

    public static class PromptUserSpec {
        public static final String SYSTEM_MESSAGE = """
            You are a friendly science writer. When writing about planets:
            - Use simple, clear language for a curious general audience.
            - Share fun facts and key traits for each planet (like color, size, moons, or atmosphere).
            """;

        public static final String USER_MESSAGE = """
               Write me story about {planetKey}
               """;

        public static final String USER_MESSAGE_PARAM_KEY = "planetKey";
        public static final String USER_MESSAGE_PARAM_VALUE = "jupiter";
    }
}
