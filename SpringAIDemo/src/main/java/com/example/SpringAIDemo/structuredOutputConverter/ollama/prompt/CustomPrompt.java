package com.example.SpringAIDemo.structuredOutputConverter.ollama.prompt;

public class CustomPrompt {

    public static class UnStructured {
        public static final String USER_MESSAGE = """
                Generate the filmography of 5 movies for actor
                """;

    }


    public static class Structured {
        public static class PromptUserSpec {
            public static final String USER_MESSAGE = """
                    Generate the filmography of 5 movies for {actor}
                    """;
            public static final String USER_MESSAGE_PARAM_KEY = "actor";
            public static final String USER_MESSAGE_PARAM_VALUE = "Tom Hanks";
        }
    }
}
