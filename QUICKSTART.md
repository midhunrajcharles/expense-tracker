# Quick Start Guide

## 🚀 Get Started in 5 Minutes

### 1. Setup Database (2 minutes)

```bash
# Start MySQL
mysql -u root -p

# Create database and load schema
mysql -u root -p < database/schema.sql
```

### 2. Update Configuration (1 minute)

Edit `expense-tracker-backend/src/main/resources/application.properties`:

```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. Start Backend (1 minute)

```bash
cd expense-tracker-backend
mvn spring-boot:run
```

Wait for: "Expense Tracker API is running!"

### 4. Open Frontend (1 minute)

Double-click: `expense-tracker-frontend/index.html`

Or with VS Code:
- Right-click `index.html`
- Select "Open with Live Server"

---

## ✅ Quick Test

1. **Add an expense**:
   - Title: "Test Expense"
   - Category: Food
   - Amount: 25.50
   - Date: Today
   - Payment: Cash
   - Click "Add Expense"

2. **Verify**: Expense appears in the list below

3. **Click "Summary"**: See total expenses

---

## 🔧 Common Issues

### Backend won't start?
- Check MySQL is running
- Verify password in `application.properties`
- Make sure port 8080 is free

### Frontend shows errors?
- Check if backend is running on http://localhost:8080
- Open browser console (F12) to see errors
- Make sure you're opening the HTML file in a browser

### No data appears?
- Verify backend is running: visit http://localhost:8080/api/expenses
- Check browser console for errors
- Try adding a new expense

---

## 📝 Next Steps

After successful setup:
1. ✅ Run the console version: `cd console-version && javac *.java && java ExpenseTrackerApp`
2. ✅ Test all CRUD operations in the web UI
3. ✅ Try filtering and searching
4. ✅ View the expense summary
5. ✅ Test API with Postman or cURL

---

**Need help?** Check the full README.md for detailed instructions.
