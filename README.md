# spring-ai-beginners



## install ollama

https://ollama.com/download



## java 17

## endpoints

http://localhost:8080/swagger-ui/index.html

## curl


# 1. Ollama RAG Chat (blocking)
curl http://localhost:8080/v2/ollama/rag/chatbot/chat/Hello

# 2. OpenAI Chat (blocking)
curl http://localhost:8080/v1/openai/chatbot/chat/Hello

# 3. OpenAI Chat Stream (non-blocking)
curl -N -H "Accept: text/event-stream" http://localhost:8080/v1/openai/chatbot/chat/stream/Hello

# 4. Ollama Chat (blocking)
curl http://localhost:8080/v1/ollama/chatbot/chat/Hello

# 5. Ollama Chat Stream (non-blocking)
curl -N -H "Accept: text/event-stream" http://localhost:8080/v1/ollama/chatbot/chat/stream/Hello

# 6. Ollama Chat Response (returns JSON object)
curl -H "Accept: application/json" http://localhost:8080/v1/ollama/chatbot/chat/chatResponse/Hello

