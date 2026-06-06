# Agent Context — Company Knowledge Bot

## Project Purpose
A Spring Boot REST API implementing a RAG (Retrieval-Augmented Generation) system
that answers company-related questions based on documents in the `docs/` folder.
Has two modes: keyword search (V1) and AI-powered with Ollama (V2).

## Architecture

### V1 - Keyword Search
```
GET /api/ask?question=...
  → KnowledgeController
  → AnswerService
  → DocumentRetriever (searches .txt files)
  → returns matching lines
```

### V2 - AI Powered (RAG + Ollama)
```
GET /api-ai/ask?question=...
  → KnowledgeControllerAi
  → RagService
  → DocumentLoader (builds context from .txt files)
  → Ollama API (http://localhost:11434/api/chat)
  → returns natural language answer
```

## Key Files
- `controller/KnowledgeController.java` → V1 API endpoint (/api/ask)
- `controller/KnowledgeControllerAi.java` → V2 API endpoint (/api-ai/ask)
- `service/AnswerService.java` → V1 answer logic
- `service/DocumentRetriever.java` → V1 keyword search in files
- `serviceAi/RagService.java` → V2 RAG logic + Ollama call
- `serviceAi/DocumentLoader.java` → V2 context builder from files
- `docs/` → knowledge base (company-faq, leave-policy, products)
- `resources/static/index.html` → Web UI

## Configuration (application.properties)
```properties
ollama.api.url=http://localhost:11434/api/chat
ollama.model=llama3.2:3b
server.port=8080
```

## How to Continue Development
- Add more knowledge: add `.txt` or `.md` files to `docs/`
- Improve V1 search: update `DocumentRetriever.findRelevantLines()`
- Improve V2 context: update `DocumentLoader.getRelevantContext()`
- Change LLM model: update `ollama.model` in `application.properties`
- Switch to OpenAI/Claude: update `RagService.callOpenAi()` method

## Run Command
```bash
ollama serve          # start Ollama first
./mvnw spring-boot:run
```