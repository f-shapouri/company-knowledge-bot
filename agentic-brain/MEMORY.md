# Memory — Decisions & Learnings

## Architecture Decisions

### Decision 1: Separate Retrieval from Answering
- **What:** Split logic into DocumentRetriever and AnswerService
- **Why:** Cleaner architecture, easier to maintain and extend
- **Result:** Controller stays thin, logic is testable

### Decision 2: Keyword-based Search
- **What:** Split question into keywords, search each separately
- **Why:** More flexible than exact phrase matching
- **Result:** Better results for multi-word questions

### Decision 3: Return 4 lines per match
- **What:** Return matched line + 3 following lines
- **Why:** Context around the answer is more useful than one line
- **Result:** More complete answers

## What I Learned
- RAG pattern: Retrieval → Augment context → Generate answer
- Importance of separating concerns in API design

## Mistakes & Fixes
- Initially KnowledgeController had all logic inside it
- Refactored to use AnswerService + DocumentRetriever for cleaner code