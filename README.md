# 💰 Expense Tracker - Full Stack Application

A complete full-stack web application for tracking and managing daily expenses, built with **Java**, **Spring Boot**, **MySQL**, **HTML**, **CSS**, and **JavaScript**.

---

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Screenshots](#screenshots)
- [Features Demonstration](#features-demonstration)
- [Technical Implementation](#technical-implementation)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)

---

## ✨ Features

### Core Functionality
- ✅ **Complete CRUD Operations** - Create, Read, Update, Delete expenses
- ✅ **Category Management** - Organize expenses by Food, Travel, Shopping, Utilities, Others
- ✅ **Payment Tracking** - Track payment methods (Cash, Card, UPI)
- ✅ **Search & Filter** - Find expenses by title or category
- ✅ **Expense Summary** - View total expenses and category-wise breakdown
- ✅ **High-Value Detection** - Automatically highlight expenses ≥ $500

### Technical Features
- ✅ **RESTful API** - Clean API architecture with proper HTTP methods
- ✅ **Data Validation** - Both client-side and server-side validation
- ✅ **Exception Handling** - Meaningful error messages and status codes
- ✅ **Responsive Design** - Works on desktop, tablet, and mobile devices
- ✅ **Real-time Updates** - Dynamic UI updates without page reload
- ✅ **Persistent Storage** - MySQL database integration
- ✅ **MVC Architecture** - Clean separation of concerns

---

## 🛠️ Technology Stack

### Backend
- **Java 17** - Core programming language
- **Spring Boot 3.2.3** - Application framework
- **Spring Data JPA** - Database access layer
- **Hibernate** - ORM framework
- **MySQL 8.0+** - Relational database
- **Maven** - Dependency management

### Frontend
- **HTML5** - Structure
- **CSS3** - Styling with custom styles and animations
- **Bootstrap 5.3** - Responsive UI framework
- **JavaScript (ES6+)** - Dynamic functionality
- **Fetch API** - Asynchronous HTTP requests

### Development Tools
- **Git** - Version control
- **VS Code / IntelliJ IDEA** - IDE
- **Postman** - API testing (optional)

---

## 📁 Project Structure

```
guvi hack/
│
├── console-version/                    # Core Java Console Application
│   ├── Expense.java                    # Expense model class
│   ├── ExpenseManager.java             # Business logic with ArrayList & HashMap
│   └── ExpenseTrackerApp.java          # Main console application
│
├── expense-tracker-backend/            # Spring Boot Backend
│   ├── src/
│   │   └── main/
│   │       ├── java/com/expensetracker/
│   │       │   ├── ExpenseTrackerApplication.java    # Main Spring Boot class
│   │       │   ├── model/
│   │       │   │   └── Expense.java                  # JPA Entity
│   │       │   ├── repository/
│   │       │   │   └── ExpenseRepository.java        # Data Access Layer
│   │       │   ├── service/
│   │       │   │   └── ExpenseService.java           # Business Logic Layer
│   │       │   ├── controller/
│   │       │   │   └── ExpenseController.java        # REST API Endpoints
│   │       │   └── exception/
│   │       │       └── GlobalExceptionHandler.java   # Error Handling
│   │       └── resources/
│   │           └── application.properties             # Configuration
│   └── pom.xml                                        # Maven dependencies
│
├── expense-tracker-frontend/          # Frontend Application
│   ├── index.html                     # Main HTML page
│   ├── css/
│   │   └── styles.css                 # Custom styles
│   └── js/
│       └── app.js                     # JavaScript functionality
│
├── database/
│   └── schema.sql                     # Database schema & sample data
│
└── README.md                          # This file
```

---

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

1. **Java Development Kit (JDK) 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **MySQL Server 8.0+**
   ```bash
   mysql --version
   ```

4. **Web Browser** (Chrome, Firefox, Edge, or Safari)

5. **Git** (optional, for cloning)
   ```bash
   git --version
   ```

---

## 🚀 Installation & Setup

### Step 1: Clone or Download the Project

```bash
cd "d:\project\guvi hack"
```

### Step 2: Set Up MySQL Database

1. **Start MySQL Server**

2. **Run the database schema**:
   ```bash
   mysql -u root -p < database/schema.sql
   ```

   Or manually:
   ```sql
   mysql -u root -p
   ```
   Then execute the SQL from `database/schema.sql`

3. **Verify database creation**:
   ```sql
   USE expense_tracker_db;
   SHOW TABLES;
   SELECT * FROM expenses;
   ```

### Step 3: Configure Database Connection

Edit `expense-tracker-backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

**Replace `YOUR_MYSQL_PASSWORD` with your actual MySQL password.**

### Step 4: Build the Backend

Navigate to the backend folder and build:

```bash
cd expense-tracker-backend
mvn clean install
```

---

## ▶️ Running the Application

### Option 1: Run Console Version (Core Java)

To test the console-based version first:

```bash
cd console-version
javac Expense.java ExpenseManager.java ExpenseTrackerApp.java
java ExpenseTrackerApp
```

This demonstrates Core Java concepts with ArrayList, HashMap, and OOP principles.

### Option 2: Run Full Stack Application

#### 1. Start the Backend Server

```bash
cd expense-tracker-backend
mvn spring-boot:run
```

**Backend will run on:** `http://localhost:8080`

You should see:
```
╔═══════════════════════════════════════════╗
║  Expense Tracker API is running!         ║
║  URL: http://localhost:8080               ║
╚═══════════════════════════════════════════╝
```

#### 2. Start the Frontend

Open `expense-tracker-frontend/index.html` in your web browser:

**Option A:** Double-click the file
**Option B:** Use Live Server in VS Code
**Option C:** Use Python HTTP server:
```bash
cd expense-tracker-frontend
python -m http.server 3000
```
Then open: `http://localhost:3000`

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api/expenses
```

### Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/expenses` | Get all expenses | - | Array of expenses |
| GET | `/api/expenses/{id}` | Get expense by ID | - | Single expense |
| POST | `/api/expenses` | Create new expense | Expense JSON | Created expense |
| PUT | `/api/expenses/{id}` | Update expense | Expense JSON | Updated expense |
| DELETE | `/api/expenses/{id}` | Delete expense | - | Success message |
| GET | `/api/expenses/filter/category/{category}` | Filter by category | - | Filtered expenses |
| GET | `/api/expenses/search?q={term}` | Search by title | - | Matching expenses |
| GET | `/api/expenses/summary` | Get summary | - | Summary object |
| GET | `/api/expenses/high-value` | Get high-value expenses | - | High-value expenses |

### Sample Request Body (POST/PUT)

```json
{
  "title": "Grocery Shopping",
  "category": "Food",
  "amount": 85.50,
  "expenseDate": "2026-03-10",
  "paymentMethod": "Card"
}
```

### Sample Response

```json
{
  "expenseId": 1,
  "title": "Grocery Shopping",
  "category": "Food",
  "amount": 85.50,
  "expenseDate": "2026-03-10",
  "paymentMethod": "Card",
  "createdDateTime": "2026-03-10T10:30:00"
}
```

### Test API with cURL

```bash
# Get all expenses
curl http://localhost:8080/api/expenses

# Create expense
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{"title":"Coffee","category":"Food","amount":5.50,"expenseDate":"2026-03-10","paymentMethod":"Cash"}'

# Get summary
curl http://localhost:8080/api/expenses/summary
```

---

## 🎯 Features Demonstration

### 1. Add Expense
- Fill in all required fields (Title, Category, Amount, Date, Payment Method)
- Click "Add Expense"
- Expense appears immediately in the list

### 2. View All Expenses
- All expenses displayed in card format
- High-value expenses (≥$500) highlighted with red border and badge
- Shows category, date, payment method, and creation time

### 3. Update Expense
- Click "Edit" button on any expense card
- Modify details in the modal
- Click "Save Changes"

### 4. Delete Expense
- Click "Delete" button
- Confirm deletion
- Expense removed immediately

### 5. Filter by Category
- Select category from dropdown
- View only expenses of that category
- Select "All Categories" to reset

### 6. Search by Title
- Enter search term in search box
- Click "Search" or press Enter
- View matching expenses
- Click "X" to clear search

### 7. View Summary
- Click "Summary" button in header
- View total expenses, number of transactions
- See category-wise breakdown with percentages

---

## 🔧 Technical Implementation

### Object-Oriented Programming (Console Version)
- **Encapsulation**: Private fields with getters/setters
- **Classes & Objects**: Expense, ExpenseManager
- **Collections**: ArrayList for list operations, HashMap for ID-based lookups
- **Methods**: Reusable methods for all operations

### Spring Boot Backend (MVC Pattern)
- **Model**: JPA entities with validation annotations
- **Repository**: Spring Data JPA repository interface
- **Service**: Business logic layer
- **Controller**: REST API endpoints
- **Exception Handling**: Global exception handler with meaningful responses

### Database Integration
- **JPA/Hibernate**: ORM for database operations
- **MySQL**: Relational database with proper schema
- **Transactions**: @Transactional for data consistency
- **Queries**: Custom JPQL queries for filtering and aggregation

### Frontend Implementation
- **Responsive Design**: Bootstrap flexbox and grid system
- **DOM Manipulation**: JavaScript for dynamic rendering
- **Event Handling**: Form submissions, button clicks
- **Async/Await**: Fetch API with promises
- **JSON**: Data exchange format
- **Validation**: Client-side form validation

---

## 🐛 Troubleshooting

### Backend Issues

**Problem**: Backend won't start
```
Solution:
1. Check if MySQL is running: mysql -u root -p
2. Verify database exists: SHOW DATABASES;
3. Check application.properties credentials
4. Ensure port 8080 is not in use
```

**Problem**: Database connection error
```
Solution:
1. Verify MySQL username and password in application.properties
2. Create database manually if needed
3. Check MySQL is running on port 3306
```

### Frontend Issues

**Problem**: Can't connect to API
```
Solution:
1. Verify backend is running (check console log)
2. Check API_URL in app.js is correct
3. Check browser console for CORS errors
4. Ensure backend has @CrossOrigin annotation
```

**Problem**: No data appears
```
Solution:
1. Open browser Developer Tools (F12)
2. Check Console for errors
3. Check Network tab for failed requests
4. Verify backend is responding: curl http://localhost:8080/api/expenses
```

### Console Version Issues

**Problem**: Compilation errors
```bash
Solution:
cd console-version
javac -encoding UTF-8 *.java
java ExpenseTrackerApp
```

---

## 🚀 Future Enhancements

Potential features to add:

- [ ] User authentication and authorization
- [ ] Multiple users with separate expense tracking
- [ ] Export expenses to PDF/Excel
- [ ] Charts and graphs for visualization
- [ ] Budget limits and alerts
- [ ] Recurring expenses
- [ ] Receipt image upload
- [ ] Dark mode toggle
- [ ] Multi-currency support
- [ ] Mobile app (React Native / Flutter)

---

## 📝 Assessment Checklist

### ✅ Core Java Console Version
- [x] Classes and Objects (Expense, ExpenseManager)
- [x] ArrayList for expense list
- [x] HashMap for ID-based operations
- [x] Loops and conditional statements
- [x] Methods for all operations
- [x] Object-Oriented Programming principles

### ✅ Spring Boot Backend
- [x] MVC architecture
- [x] RESTful API design
- [x] Spring Data JPA
- [x] MySQL database integration
- [x] Exception handling
- [x] HTTP status codes
- [x] Request/Response validation

### ✅ Frontend
- [x] HTML5 structure
- [x] CSS3 styling with Bootstrap
- [x] Responsive design (Flexbox/Grid)
- [x] JavaScript DOM manipulation
- [x] Event handling
- [x] Fetch API with async/await
- [x] JSON data exchange
- [x] Client-side validation

### ✅ Integration
- [x] Full stack connectivity
- [x] CRUD operations end-to-end
- [x] Dynamic UI updates
- [x] No page reload for operations
- [x] Persistent database storage
- [x] Error handling across layers

### ✅ Special Features
- [x] Filter by category
- [x] Search by title
- [x] Expense summary
- [x] High-value expense detection
- [x] Category-wise breakdown
- [x] Date tracking
- [x] Payment method tracking

---

## 👤 Author

**GUVI Hackathon Project**
- Full Stack Expense Tracker
- Demonstrates: Java, Spring Boot, MySQL, HTML, CSS, JavaScript
- Date: March 2026

---

## 📄 License

This project is created for educational purposes as part of the GUVI Full Stack Development assessment.

---

## 🙏 Acknowledgments

- **Spring Boot** - Amazing framework for rapid development
- **Bootstrap** - Responsive UI components
- **Font Awesome** - Beautiful icons
- **MySQL** - Reliable database system

---

## 📞 Support

For issues or questions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Review the code comments for implementation details
3. Test API endpoints using the provided cURL commands
4. Check browser console and server logs for errors

---

**Happy Expense Tracking! 💰**
