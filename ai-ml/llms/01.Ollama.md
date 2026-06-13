
    
```
Model Downloaded
       │
       ▼
Stored on Disk
       │
       ▼
ollama run
       │
       ▼
Loaded into RAM/GPU
       │
       ▼
Inference
       │
       ▼
ollama stop
       │
       ▼
Removed from RAM
       │
       ▼
Still available on Disk

```

### What is Ollama?
Ollama is a local LLM runtime.

It allows you to:

* Download LLMs
* Run them locally
* Expose them through APIs
* Integrate them into applications

Examples:

* Run Llama 3
* Run Mistral
* Run Gemma
* Run coding models
* Run embedding models

without calling cloud APIs.

Ollama is a Docker of LLM

#### Benefits:
* Free inference
* Better privacy
* Offline usage
* Fast prototyping
* provide single command for any model to setup

Default API:
http://localhost:11434

#### Architecture

```
        ┌────────────────┐
        │ Application    │
        │Java/Python/API │
        └──────┬─────────┘
               │
           REST API
               │
               ▼
        ┌───────────────┐
        │ Ollama Server │
        └──────┬────────┘
               │
          Loads Model
               │
               ▼
      ┌─────────────────┐
      │ Llama/Mistral   │
      │ Gemma etc       │
      └─────────────────┘
               │
        Generates Text
               │
               ▼
        Result/Response

```

### Important Command

| Task           | Command                  |
|----------------|--------------------------|
| Download Model | ollama pull <Model-Name> |
| Run Model      | ollama run <Model-Name>  |
| List Models    | ollama list              |
| Start Server   | ollama serve             | 

### API Architecture

```
User
 ↓
Spring Boot
 ↓
Ollama API
 ↓
Model
 ↓
Response
```

api -POST /api/generate

Request -
{
"model":"llama3",
"prompt":"Explain Java Streams"
}

Response -
{
"response":"Java Streams are..."
}


