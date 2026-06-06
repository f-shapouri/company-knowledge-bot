# Memory — Decisions & Learnings

## Architecture Decisions

### Decision 1: Two separate modes (V1 and V2)
- **What:** Built V1 (keyword search) and V2 (AI-powered) as separate endpoints
- **Why:** V1 works without any external dependency; V2 adds real AI capability
- **Result:** Users can compare both modes via the UI

### Decision 2: Separate Retrieval from Answering
- **What:** Split logic into DocumentRetriever/DocumentLoader and AnswerService/RagService
- **Why:** Cleaner architecture, easier to maintain and extend
- **Result:** Controller stays thin, logic is testable and replaceable

### Decision 3: Keyword scoring in V1
- **What:** Split question into keywords, score each line by keyword matches
- **Why:** More flexible than exact phrase matching
- **Result:** Better results for multi-word questions

### Decision 4: Return 4 lines per match in V1
- **What:** Return matched line + 3 following lines
- **Why:** Context around the answer is more useful than one line
- **Result:** More complete answers

### Decision 5: Use Ollama instead of OpenAI
- **What:** Used Ollama (local LLM) instead of OpenAI API
- **Why:** OpenAI API was not accessible due to network restrictions (filtered).
  Learned about Ollama from an educational video and applied it here.
- **Result:** Fully local, free, and no internet required for AI answers

### Decision 6: Prompt format matters
- **What:** Changed prompt format to use CONTEXT/QUESTION/ANSWER structure
- **Why:** Initial prompt with Instructions block confused the model
- **Result:** Model now answers directly and accurately from documents

## What I Learned
- RAG pattern: Retrieval → Augment context → Generate answer
- Prompt engineering: clear structure (CONTEXT/QUESTION/ANSWER) gives better results
- Ollama: local LLM that runs on your own machine, free and private

## Mistakes & Fixes
- Initially KnowledgeController had all logic inside it → refactored to services
- First prompt format confused Ollama → fixed with cleaner CONTEXT/QUESTION/ANSWER structure
- Keyword length threshold was 4 → lowered to 3 to support short terms like "ERP"
- Used OpenAI references in code → cleaned up and replaced with Ollama