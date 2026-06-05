# Company Knowledge Bot

A Spring Boot REST API that answers questions based on company documents.
Built as a mini RAG (Retrieval-Augmented Generation) system.

## How it Works
1. User asks a question via HTTP GET request
2. System searches through company documents in `docs/` folder
3. Returns relevant answer as JSON

## Tech Stack
- Java 25, Spring Boot 4.x
- Architecture: Controller → AnswerService → DocumentRetriever

## How to Run
```bash
./mvnw spring-boot:run
```

## API Usage
GET http://localhost:8080/api/ask?question=sick leave

**Response:**
```json
{
  "question": "sick leave",
  "answer": "Sick Leave:\n- Employees are entitled to 10 days..."
}
```

## Project Structure
docs/                    → company knowledge files
src/
controller/            → API layer
service/               → RAG logic (retrieval + answering)
agentic-brain/           → project context and memory

## AI Tools Used
- **Claude (Anthropic):** assisted with architecture design and code structure