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



## Convert Output To Specific Format


structuredOutputConverter


#### 1 Output is String

```

curl -X 'GET' \
  'http://localhost:8080/ollama/structuredOutputConverter/example1/chat/content?userMessage=Generate%20the%20filmography%20of%205%20movies%20for%20random%20actor%0A' \
  -H 'accept: */*'

```



#### 2 Output is Chat Response

```
curl -X 'GET' \
  'http://localhost:8080/ollama/structuredOutputConverter/example2/chat/chatResponse?userMessage=Generate%20the%20filmography%20of%205%20movies%20for%20random%20actor%0A' \
  -H 'accept: */*'
```


#### 3 Output is Chat Client Response


```
curl -X 'GET' \
  'http://localhost:8080/ollama/structuredOutputConverter/example3/chat/chatClientResponse?userMessage=Generate%20the%20filmography%20of%205%20movies%20for%20random%20actor%0A' \
  -H 'accept: */*'
```


#### 4 Output is Custom Class Formatted With StructuredOutputConverter

Chat reponse is parsed automatically.


```
curl -X 'GET' \
  'http://localhost:8080/ollama/structuredOutputConverter/example4/chat/structuredOutputFormatter?userMessage=Generate%20the%20filmography%20of%205%20movies%20for%20%7Bactor%7D%0A&paramKey=actor&paramValue=Tom%20Hanks' \
  -H 'accept: */*'
```




## Multimodality - Image / Text / Audio


#### 1 Image to Text


```
curl -X 'GET' \
  'http://localhost:8080/ollama/multimodality/example1/chat/imageToText?userMessage=Could%20you%20please%20describe%20what%20you%20see%20in%20the%20following%20image%0A' \
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



## Rag - Document



#### 1 Out of rule context

```

curl -X 'GET' \
  'http://localhost:8080/ollama/rag/chatbot/example1/chat/outOfContext?userMessage=Hello%20How%20are%20you%0A' \
  -H 'accept: */*'
```


#### 2 In context but rule does not allowed to share information

```
curl -X 'GET' \
  'http://localhost:8080/ollama/rag/chatbot/example2/chat/inContextNotPermitted?userMessage=Tell%20us%20something%20about%20Research%20and%20Development%0A' \
  -H 'accept: */*'

```

#### 3 In Context

Rag Store is ready.

```
curl -X 'GET' \
  'http://localhost:8080/ollama/rag/chatbot/example3/chat/inContext?userMessage=Tell%20us%20something%20about%20Human%20Resources%20Department%0A' \
  -H 'accept: */*'

```

---


## 1. OpenAI Chatbot - Simple Chat
curl -X GET "http://localhost:8080/openai/chatbot/chat/Hello"

## 2. OpenAI Chatbot - Stream Chat
curl -X GET "http://localhost:8080/openai/chatbot/chat/stream/Hello"


## 7. Ollama RAG Chatbot
curl -X GET "http://localhost:8080/ollama/rag/chatbot/chat/Hello"


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