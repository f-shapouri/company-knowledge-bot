# Company Knowledge Bot

A Spring Boot REST API that answers questions based on company documents.
Built as a **RAG (Retrieval-Augmented Generation)** system with two modes:
- **V1:** Keyword-based search (no AI)
- **V2:** Real AI answers powered by **Ollama** (local LLM)

## How it Works

### V1 - Keyword Search
1. User asks a question via HTTP GET
2. `DocumentRetriever` searches `.txt` files in `docs/` folder
3. Returns matching lines as JSON

### V2 - AI Powered (RAG + Ollama)
1. User asks a question via HTTP GET
2. `DocumentLoader` retrieves relevant context from `docs/` folder
3. `RagService` builds a prompt with context + question
4. Ollama (local LLM) generates a natural language answer
5. Answer returned as JSON

## Tech Stack
- Java 25, Spring Boot 4.x
- Ollama (local LLM — llama3.2:3b model)
- Architecture: Controller → Service → DocumentLoader/Retriever → Ollama

## Prerequisites
- Java 25
- Ollama installed and running: `ollama serve`
- Model pulled: `ollama pull llama3.2:3b`

## How to Run
```bash
# Start Ollama
ollama serve

# Run the app
./mvnw spring-boot:run
```

Open browser: `http://localhost:8080`

## API Usage

### V1 - Keyword Search
```
GET http://localhost:8080/api/ask?question=sick leave
```

### V2 - AI Powered
```
GET http://localhost:8080/api-ai/ask?question=sick leave
```

**Response:**
```json
{
  "question": "sick leave",
  "answer": "Based on the company policy, employees are entitled to 10 days of paid sick leave per year..."
}
```

## Project Structure
```
docs/                      → company knowledge files
src/
  controller/              → API layer (V1 + V2 endpoints)
  service/                 → V1 logic (keyword search)
  serviceAi/               → V2 logic (RAG + Ollama)
resources/static/          → Web UI (index.html)
agentic-brain/             → project context and memory
```

## AI Tools Used
- **Claude (Anthropic):** assisted with architecture design, code structure, and guidance
- **Ollama (llama3.2:3b):** local LLM used for natural language answer generation