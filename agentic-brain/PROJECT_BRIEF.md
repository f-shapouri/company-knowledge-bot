# Project Brief — Company Knowledge Bot

## What is this?
A Spring Boot REST API that answers questions based on company documents.
Users ask questions via HTTP GET request, and the system searches through
text files in the docs/ folder to find relevant answers.

## MVP (Minimum Viable Product)
- REST endpoint: GET /api/ask?question=your question
- Searches 3 company documents: FAQ, Leave Policy, Products
- Returns JSON response with question and answer

## Tech Stack
- Java 25, Spring Boot 4.x
- Architecture: Controller → AnswerService → DocumentRetriever
- No external AI API (keyword-based RAG retrieval)

## How to Run
1. Clone the repo
2. Run: ./mvnw spring-boot:run
3. Open browser: http://localhost:8080/api/ask?question=sick leave

## Project Structure
```
docs/               → company knowledge files
src/
  controller/       → KnowledgeController (API layer)
  service/          → AnswerService + DocumentRetriever (logic layer)
agentic-brain/      → project context and memory files
```