# Project Brief — Company Knowledge Bot

## What is this?
A Spring Boot REST API that answers company-related questions based on
documents stored in the `docs/` folder. Built as a RAG system with two modes.

## MVP (Minimum Viable Product)
- **V1:** REST endpoint `GET /api/ask?question=...` — keyword search, no AI
- **V2:** REST endpoint `GET /api-ai/ask?question=...` — real AI answers via Ollama
- Web UI to compare both modes
- 3 company documents: FAQ, Leave Policy, Products
- Returns JSON response with question and answer

## Tech Stack
- Java 25, Spring Boot 4.x
- Ollama (local LLM — llama3.2:3b)
- Architecture: Controller → Service → DocumentLoader/Retriever → Ollama

## How to Run
1. Install Ollama: `sudo snap install ollama`
2. Pull model: `ollama pull llama3.2:3b`
3. Start Ollama: `ollama serve`
4. Clone the repo
5. Run: `./mvnw spring-boot:run`
6. Open browser: `http://localhost:8080`

## Project Structure
```
docs/                  → company knowledge files
src/
  controller/          → KnowledgeController (V1) + KnowledgeControllerAi (V2)
  service/             → AnswerService + DocumentRetriever (V1 logic)
  serviceAi/           → RagService + DocumentLoader (V2 AI logic)
  resources/static/    → Web UI (index.html)
agentic-brain/         → project context and memory files
```

## Why Ollama?
OpenAI API was not accessible due to network restrictions.
Discovered Ollama from an educational video — it runs locally,
is completely free, and requires no internet connection.