# API Testing Guide

## Using cURL

### Get All Expenses
```bash
curl http://localhost:8080/api/expenses
```

### Get Expense by ID
```bash
curl http://localhost:8080/api/expenses/1
```

### Create New Expense
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test Expense",
    "category": "Food",
    "amount": 25.50,
    "expenseDate": "2026-03-10",
    "paymentMethod": "Cash"
  }'
```

### Update Expense
```bash
curl -X PUT http://localhost:8080/api/expenses/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Expense",
    "category": "Travel",
    "amount": 100.00,
    "expenseDate": "2026-03-10",
    "paymentMethod": "Card"
  }'
```

### Delete Expense
```bash
curl -X DELETE http://localhost:8080/api/expenses/1
```

### Filter by Category
```bash
curl http://localhost:8080/api/expenses/filter/category/Food
```

### Search by Title
```bash
curl "http://localhost:8080/api/expenses/search?q=grocery"
```

### Get Summary
```bash
curl http://localhost:8080/api/expenses/summary
```

### Get High-Value Expenses
```bash
curl http://localhost:8080/api/expenses/high-value
```

---

## Using Postman

### Setup
1. Open Postman
2. Create a new Collection: "Expense Tracker"
3. Set Base URL as variable: `http://localhost:8080`

### Sample Requests

#### 1. GET All Expenses
- Method: GET
- URL: `{{baseUrl}}/api/expenses`
- Headers: None needed

#### 2. POST Create Expense
- Method: POST
- URL: `{{baseUrl}}/api/expenses`
- Headers: `Content-Type: application/json`
- Body (raw JSON):
```json
{
  "title": "Coffee",
  "category": "Food",
  "amount": 5.50,
  "expenseDate": "2026-03-10",
  "paymentMethod": "Cash"
}
```

#### 3. PUT Update Expense
- Method: PUT
- URL: `{{baseUrl}}/api/expenses/1`
- Headers: `Content-Type: application/json`
- Body (raw JSON):
```json
{
  "title": "Updated Coffee",
  "category": "Food",
  "amount": 6.00,
  "expenseDate": "2026-03-10",
  "paymentMethod": "Card"
}
```

#### 4. DELETE Expense
- Method: DELETE
- URL: `{{baseUrl}}/api/expenses/1`
- Headers: None needed

---

## Expected Responses

### Success Response (200 OK)
```json
{
  "expenseId": 1,
  "title": "Grocery Shopping",
  "category": "Food",
  "amount": 85.50,
  "expenseDate": "2026-03-08",
  "paymentMethod": "Card",
  "createdDateTime": "2026-03-08T10:30:00"
}
```

### Error Response (400 Bad Request)
```json
{
  "timestamp": "2026-03-10T12:30:00",
  "status": 400,
  "errors": {
    "title": "Title is mandatory",
    "amount": "Amount must be greater than 0"
  },
  "message": "Validation failed"
}
```

### Error Response (404 Not Found)
```json
{
  "timestamp": "2026-03-10T12:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Expense not found with id: 99"
}
```

---

## Validation Testing

### Test Invalid Data

#### Missing Required Fields
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "",
    "category": "Food",
    "amount": 0
  }'
```

Expected: 400 Bad Request with validation errors

#### Invalid Category
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "category": "InvalidCategory",
    "amount": 50.00,
    "expenseDate": "2026-03-10",
    "paymentMethod": "Cash"
  }'
```

Expected: 400 Bad Request - Invalid category

#### Negative Amount
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Test",
    "category": "Food",
    "amount": -10.00,
    "expenseDate": "2026-03-10",
    "paymentMethod": "Cash"
  }'
```

Expected: 400 Bad Request - Amount must be greater than 0

---

## Summary Endpoint Response

```json
{
  "totalExpenses": 2052.30,
  "numberOfExpenses": 7,
  "categoryBreakdown": {
    "Food": 133.55,
    "Travel": 675.50,
    "Shopping": 1200.00,
    "Utilities": 75.00,
    "Others": 0.00
  }
}
```
