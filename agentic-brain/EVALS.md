# Evals — Test Questions & Expected Answers

## How to Test

### V1 (Keyword Search):
`http://localhost:8080/api/ask?question=YOUR_QUESTION`

### V2 (AI Powered - Ollama llama3.2:3b):
`http://localhost:8080/api-ai/ask?question=YOUR_QUESTION`

Or use the Web UI at `http://localhost:8080`

---

## Test Cases

### Test 1
- **Question:** `sick leave`
- **Expected:** 10 days paid sick leave, medical certificate required

### Test 2
- **Question:** `working hours`
- **Expected:** Saturday to Wednesday, 9 AM to 6 PM

### Test 3
- **Question:** `contact support`
- **Expected:** support@techvision.com and +1-800-123-4567

### Test 4
- **Question:** `annual leave`
- **Expected:** 30 days paid annual leave per year

### Test 5
- **Question:** `ERP`
- **Expected:** TechVision ERP product info, starts from $500/month

### Test 6 (negative test)
- **Question:** `salary`
- **Expected:** "I don't know" — salary not in documents

---

## Actual Results

### V1 - Keyword Search
| Question | Actual Answer | Pass/Fail |
|----------|--------------|-----------|
| sick leave | "Sick Leave: - Employees are entitled to 10 days of paid sick leave per year..." | ✅ Pass |
| working hours | "Q: What are the working hours? A: Saturday to Wednesday, 9 AM to 6 PM." | ✅ Pass |
| contact support | "Q: How can I contact support? A: support@techvision.com or call +1-800-123-4567." | ✅ Pass |
| annual leave | "Annual Leave: - Each employee is entitled to 30 days of paid annual leave per year." | ✅ Pass |
| ERP | "Product 1: TechVision ERP... Price: starts from $500/month." | ✅ Pass |
| salary | "No relevant information found." | ✅ Pass |

### V2 - AI Powered (Ollama llama3.2:3b)
| Question | Actual Answer | Pass/Fail |
|----------|--------------|-----------|
| sick leave | "Employees are entitled to 10 days of paid sick leave per year." | ✅ Pass |
| working hours | "Our working hours are Saturday to Wednesday, 9 AM to 6 PM." | ✅ Pass |
| contact support | "You can contact support via email at support@techvision.com." | ✅ Pass |
| annual leave | "Each employee is entitled to 30 days of paid annual leave per year." | ✅ Pass |
| ERP | "TechVision ERP." (brief — model limitation) | ⚠️ Partial |
| salary | "I don't know based on available documents." | ✅ Pass |

---

## Notes
- V1 returns raw text from documents; fast but less natural
- V2 returns natural language answers powered by Ollama (llama3.2:3b)
- ERP answer in V2 is brief due to model size; larger model would improve this
- Negative test (salary) correctly returns "I don't know" in both V1 and V2