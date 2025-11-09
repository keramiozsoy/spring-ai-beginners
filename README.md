## spring-ai-beginners


# install ollama

https://ollama.com/download


# endpoints

http://localhost:8080/swagger-ui/index.html


# example model names

#### openai

- org.springframework.ai.openai.api.OpenAiApi.ChatModel
- org.springframework.ai.openai.api.OpenAiAudioApi.WhisperModel
- org.springframework.ai.openai.api.OpenAiImageApi.ImageModel


#### anthropic

- org.springframework.ai.anthropic.api.AnthropicApi.ChatModel

#### ollama

- org.springframework.ai.ollama.api.OllamaModel



# curl


## Chat


ollama-chatbot-controller

#### 1 Blocking-Request

```

curl -X 'GET' \
  'http://localhost:8080/ollama/chatbot/example1/chat?userMessage=Hello' \
  -H 'accept: */*'

```

#### 2 Non-Blocking-Request

--no-buffer

```

curl --no-buffer -X 'GET' \
  'http://localhost:8080/ollama/chatbot/example2/chat/stream?userMessage=Hello' \
  -H 'accept: */*'
```


#### 3 Chat Reponse / Token

Response returned by AI provider. You can change model and test by yourself.

```
curl -X 'GET' \
  'http://localhost:8080/ollama/chatbot/example3/chat/chatResponse?userMessage=Hello' \
  -H 'accept: */*'

```




## Chat Memory

ollama-chat-memory-controller


#### 1 Ask Before Info


```
curl -X 'GET' \
  'http://localhost:8080/ollama/chat/memory/example1/askBeforeInfo?userMessage=What%20is%20the%20name%20of%20asdfa%3F' \
  -H 'accept: */*'
```


#### 2 Share Info

```
curl -X 'GET' \
  'http://localhost:8080/ollama/chat/memory/example2/shareInfo?userMessage=Hi%2C%20The%20cat%20name%20is%20Blue.%0A' \
  -H 'accept: */*'
```

#### 3 Ask About Info

```
curl -X 'GET' \
  'http://localhost:8080/ollama/chat/memory/example3/askAboutInfo?userMessage=What%20is%20the%20name%20of%20cat%3F%0A' \
  -H 'accept: */*'
```




## Prompt

ollama-prompt-engineering-controller


#### 1 User Message Not In Scope

```
curl -X 'GET' \
  'http://localhost:8080/ollama/promptEngineering/example1/chat/userMessageNotInScope?systemMessage=You%20are%20a%20friendly%20math%20teacher%20helping%20a%20child%20who%20is%20learning%20math%20for%20the%20first%20time.%0AYou%20can%20only%20discuss%20about%20the%20math.%0AOnly%20answer%20basic%20math%20questions%20like%20counting%2C%20addition%2C%20subtraction%2C%20and%20shapes.%0AIf%20the%20question%20is%20outside%20this%20scope%2C%20politely%20reply%3A%20%22You%20are%20out%20of%20scope%2C%20please%20ask%20again.%22%0A&userMessage=What%20is%20the%20weather%20today%0A' \
  -H 'accept: */*'
```


#### 2 User Message In Scope

```
curl -X 'GET' \
  'http://localhost:8080/ollama/promptEngineering/example2/chat/userMessageInScope?systemMessage=You%20are%20a%20friendly%20math%20teacher%20helping%20a%20child%20who%20is%20learning%20math%20for%20the%20first%20time.%0AYou%20can%20only%20discuss%20about%20the%20math.%0AOnly%20answer%20basic%20math%20questions%20like%20counting%2C%20addition%2C%20subtraction%2C%20and%20shapes.%0AIf%20the%20question%20is%20outside%20this%20scope%2C%20politely%20reply%3A%20%22You%20are%20out%20of%20scope%2C%20please%20ask%20again.%22%0A&userMessage=teach%20me%20four%20plus%20five%0A' \
  -H 'accept: */*'

```


#### 3 User Message Prompt Type

```

curl -X 'GET' \
  'http://localhost:8080/ollama/promptEngineering/example3/chat/userMessagePromptType?systemMessage=You%20are%20a%20friendly%20math%20teacher%20helping%20a%20child%20who%20is%20learning%20math%20for%20the%20first%20time.%0AYou%20can%20only%20discuss%20about%20the%20math.%0AOnly%20answer%20basic%20math%20questions%20like%20counting%2C%20addition%2C%20subtraction%2C%20and%20shapes.%0AIf%20the%20question%20is%20outside%20this%20scope%2C%20politely%20reply%3A%20%22You%20are%20out%20of%20scope%2C%20please%20ask%20again.%22%0A&userMessage=teach%20me%20four%20plus%20five%0A' \
  -H 'accept: */*'

```


#### 4 Prompt User Spec Param Key
```

curl -X 'GET' \
  'http://localhost:8080/ollama/promptEngineering/example4/chat/promptUserSpecParamKey?systemMessage=You%20are%20a%20friendly%20science%20writer.%20When%20writing%20about%20planets%3A%0A-%20Use%20simple%2C%20clear%20language%20for%20a%20curious%20general%20audience.%0A-%20Share%20fun%20facts%20and%20key%20traits%20for%20each%20planet%20%28like%20color%2C%20size%2C%20moons%2C%20or%20atmosphere%29.%0A&userMessage=Write%20me%20story%20about%20%7BplanetKey%7D%0A&paramKey=planetKey&paramValue=jupiter' \
  -H 'accept: */*'

```


---


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