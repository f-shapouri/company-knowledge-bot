# Evals — Test Questions & Expected Answers

## How to Test
Run the app and open in browser:
`http://localhost:8080/api/ask?question=YOUR_QUESTION`

---

## Test Cases

### Test 1
- **Question:** `sick leave`
- **Expected:** Information about sick leave days and certificate requirement
- **URL:** `http://localhost:8080/api/ask?question=sick leave`

### Test 2
- **Question:** `working hours`
- **Expected:** Information about Saturday to Wednesday, 9 AM to 6 PM
- **URL:** `http://localhost:8080/api/ask?question=working hours`

### Test 3
- **Question:** `contact support`
- **Expected:** support@techvision.com and phone +1-800-123-4567
- **URL:** `http://localhost:8080/api/ask?question=contact support`

### Test 4
- **Question:** `annual leave`
- **Expected:** 30 days paid annual leave information
- **URL:** `http://localhost:8080/api/ask?question=annual leave`

### Test 5
- **Question:** `product price`
- **Expected:** Product pricing information
- **URL:** `http://localhost:8080/api/ask?question=product price`

---

## Results Log
| Question | Expected | Actual | Pass/Fail |
|----------|----------|--------|-----------|
| sick leave | sick leave info | ✅ Found in leave-policy.txt | Pass |
| working hours | hours info | ✅ Found in company-faq.txt | Pass |
| contact support | contact info | ✅ Found in company-faq.txt | Pass |
| annual leave | 30 days info | ✅ Found in leave-policy.txt | Pass |
| product price | price info | ✅ Found in products.txt | Pass |