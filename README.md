## spring-ai-beginners


# install ollama

https://ollama.com/download


# endpoints

http://localhost:8080/swagger-ui/index.html

# curl


## 1. OpenAI Chatbot - Simple Chat
curl -X GET "http://localhost:8080/openai/chatbot/chat/Hello"

## 2. OpenAI Chatbot - Stream Chat
curl -X GET "http://localhost:8080/openai/chatbot/chat/stream/Hello"

## 3. Ollama Structured Output (v4)
curl -G "http://localhost:8080/ollama/structuredOutputConverter/4/chat/structuredOutputFormatter" \
  --data-urlencode "userMessage=Generate the filmography of 5 movies for {actor}" \
  --data-urlencode "paramKey=actor" \
  --data-urlencode "paramValue=Tom Hanks"

## 4. Ollama ChatClient Response (v3)
curl -G "http://localhost:8080/ollama/structuredOutputConverter/3/chat/chatClientResponse" \
  --data-urlencode "userMessage=Generate the filmography of 5 movies for actor"

## 5. Ollama Chat Response (v2)
curl -G "http://localhost:8080/ollama/structuredOutputConverter/2/chat/chatResponse" \
  --data-urlencode "userMessage=Generate the filmography of 5 movies for actor"

## 6. Ollama Content Output (v1)
curl -G "http://localhost:8080/ollama/structuredOutputConverter/1/chat/content" \
  --data-urlencode "userMessage=Generate the filmography of 5 movies for actor"

## 7. Ollama RAG Chatbot
curl -X GET "http://localhost:8080/ollama/rag/chatbot/chat/Hello"

## 8. Prompt Engineering - User Spec Param
curl -G "http://localhost:8080/ollama/promptEngineering/2/chat/promptUserSpecParamKey" \
  --data-urlencode "systemMessage=You are a friendly science writer. When writing about planets: Use simple, clear language..." \
  --data-urlencode "userMessage=Write me story about {planetKey}" \
  --data-urlencode "paramKey=planetKey" \
  --data-urlencode "paramValue=jupiter"

## 9. Prompt Engineering - Out of Scope Message
curl -G "http://localhost:8080/ollama/promptEngineering/1/chat/userMessageNotInScope" \
  --data-urlencode "systemMessage=You are a friendly math teacher helping a child who is learning math for the first time..." \
  --data-urlencode "userMessage=What is the weather today"

## 10. Multimodality - Image to Text
curl -X GET "http://localhost:8080/ollama/multimodality/1/chat/imageToText"

## 11. Ollama Chatbot Basic
curl -X GET "http://localhost:8080/ollama/chatbot/chat/Hello"

## 12. Ollama Chatbot Stream
curl -X GET "http://localhost:8080/ollama/chatbot/chat/stream/Hello"

## 13. Ollama Chatbot Structured Response
curl -X GET "http://localhost:8080/ollama/chatbot/chat/chatResponse/Hello"

## 14. Anthropic Chatbot
curl -X GET "http://localhost:8080/anthropic/chatbot/chat/Hello"

## 15. Anthropic Chatbot Stream
curl -X GET "http://localhost:8080/anthropic/chatbot/chat/stream/Hello"