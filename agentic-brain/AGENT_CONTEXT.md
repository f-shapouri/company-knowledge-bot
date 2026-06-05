# Agent Context — Company Knowledge Bot

## Project Purpose
This is a Spring Boot REST API that implements a simple RAG
(Retrieval-Augmented Generation) system. It searches company
documents to answer user questions.

## How it Works
1. User sends GET request to /api/ask?question=...
2. KnowledgeController receives the request
3. AnswerService calls DocumentRetriever
4. DocumentRetriever searches .txt files in docs/ folder
5. Matching lines are returned as the answer

## Key Files
- `src/.../controller/KnowledgeController.java` -> API endpoint
- `src/.../service/AnswerService.java` → answer logic
- `src/.../service/DocumentRetriever.java` → file search logic
- `docs/` → knowledge base (company-faq, leave-policy, products)

## How to Continue Development
- To add more knowledge: add .txt or .md files to docs/
- To improve search: update DocumentRetriever.findRelevantLines()
- To add LLM: inject Claude or OpenAI API in AnswerService

## Run Command
```
./mvnw spring-boot:run
```