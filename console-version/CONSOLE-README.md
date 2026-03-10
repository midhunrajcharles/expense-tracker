# Running Instructions for Console Version

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line / Terminal access

## Step-by-Step Instructions

### Windows (PowerShell or Command Prompt)

1. **Navigate to the console-version folder**:
   ```powershell
   cd "d:\project\guvi hack\console-version"
   ```

2. **Compile all Java files**:
   ```powershell
   javac Expense.java ExpenseManager.java ExpenseTrackerApp.java
   ```

3. **Run the application**:
   ```powershell
   java ExpenseTrackerApp
   ```

### Linux / Mac (Terminal)

1. **Navigate to the console-version folder**:
   ```bash
   cd "/d/project/guvi hack/console-version"
   ```

2. **Compile all Java files**:
   ```bash
   javac Expense.java ExpenseManager.java ExpenseTrackerApp.java
   ```

3. **Run the application**:
   ```bash
   java ExpenseTrackerApp
   ```

## Using the Console Application

Once the application starts, you'll see:

```
╔════════════════════════════════════════╗
║   EXPENSE TRACKER - CONSOLE VERSION   ║
╚════════════════════════════════════════╝

✓ Sample data loaded successfully!

═══════════════ MENU ═══════════════
1. Add New Expense
2. View All Expenses
3. Update Expense
4. Delete Expense
5. Filter by Category
6. Search by Title
7. View Summary
8. Exit
════════════════════════════════════

Enter your choice:
```

### Menu Options

#### 1. Add New Expense
- Enter title (e.g., "Lunch")
- Choose category: Food, Travel, Shopping, Utilities, Others
- Enter amount (e.g., 25.50)
- Enter date in format: yyyy-MM-dd (e.g., 2026-03-10)
- Choose payment method: Cash, Card, UPI

#### 2. View All Expenses
- Displays all expenses with full details
- High-value expenses (≥$500) are marked with ⚠️

#### 3. Update Expense
- Enter expense ID to update
- Enter new values (or press Enter to skip a field)

#### 4. Delete Expense
- Enter expense ID to delete
- Confirms deletion

#### 5. Filter by Category
- Enter category name
- Shows only expenses from that category

#### 6. Search by Title
- Enter search term
- Shows expenses with matching titles (case-insensitive)

#### 7. View Summary
- Shows total expenses
- Shows number of expenses
- Shows category-wise breakdown

#### 8. Exit
- Exits the application

## Sample Interaction

```
Enter your choice: 1

--- Add New Expense ---
Enter title: Coffee
Categories: Food, Travel, Shopping, Utilities, Others
Enter category: Food
Enter amount: 5.50
Enter expense date (yyyy-MM-dd): 2026-03-10
Payment Methods: Cash, Card, UPI
Enter payment method: Cash
✓ Expense added successfully! ID: 6

Enter your choice: 2

========== ALL EXPENSES ==========
ID: 1 | Title: Grocery Shopping | Category: Food | Amount: $85.50 | Date: 2026-03-08 | Payment: Card | Created: 2026-03-08T10:30:00
ID: 2 | Title: Flight Tickets | Category: Travel | Amount: $650.00 | Date: 2026-03-05 | Payment: Card | Created: 2026-03-05T14:20:00
⚠️  [HIGH VALUE] ID: 4 | Title: New Laptop | Category: Shopping | Amount: $1200.00 | Date: 2026-03-01 | Payment: Card | Created: 2026-03-01T11:45:00
ID: 6 | Title: Coffee | Category: Food | Amount: $5.50 | Date: 2026-03-10 | Payment: Cash | Created: 2026-03-10T15:23:45
==================================
```

## Core Java Concepts Demonstrated

### 1. Classes and Objects
- **Expense class**: Represents an expense with properties and methods
- **ExpenseManager class**: Manages all expense operations
- Object creation and manipulation

### 2. Collections
- **ArrayList**: Stores expenses in order
  ```java
  private ArrayList<Expense> expenseList;
  ```
- **HashMap**: Quick ID-based lookups
  ```java
  private HashMap<Integer, Expense> expenseMap;
  ```

### 3. OOP Principles
- **Encapsulation**: Private fields with public getters/setters
- **Abstraction**: ExpenseManager hides implementation details
- **Methods**: Reusable code blocks for operations

### 4. Control Structures
- **Loops**: 
  - `for` loops to iterate through expenses
  - `while` loops for menu navigation
- **Conditionals**: 
  - `if-else` for validation
  - `switch-case` for menu selection

### 5. Data Validation
- Checking for empty strings
- Validating numeric inputs
- Date format validation
- Category and payment method validation

### 6. Date/Time Handling
- `LocalDate` for expense dates
- `LocalDateTime` for creation timestamps
- `DateTimeFormatter` for parsing

### 7. String Manipulation
- `trim()` to remove whitespace
- `toLowerCase()` / `toUpperCase()` for case-insensitive comparison
- `contains()` for search functionality

### 8. Lambda Expressions & Streams (Java 8+)
```java
List<Expense> filtered = expenseList.stream()
    .filter(e -> e.getCategory().equalsIgnoreCase(category))
    .collect(Collectors.toList());
```

## Troubleshooting

### Error: "javac is not recognized"
**Solution**: 
- Ensure JDK is installed
- Add Java to PATH environment variable
- Restart terminal after installing Java

### Error: "Could not find or load main class"
**Solution**:
- Make sure you're in the correct directory
- Recompile all files: `javac *.java`
- Run with correct class name: `java ExpenseTrackerApp`

### Error: Date parsing exception
**Solution**:
- Use correct date format: `yyyy-MM-dd`
- Example: `2026-03-10` not `03/10/2026`

## Testing Checklist

- [ ] Add a new expense
- [ ] View all expenses
- [ ] Update an existing expense
- [ ] Delete an expense
- [ ] Filter expenses by category
- [ ] Search expenses by title
- [ ] View expense summary
- [ ] Test validation (try empty title, negative amount)
- [ ] Test high-value expense detection (add expense ≥ $500)

---

**Note**: This console version demonstrates Core Java concepts. The full-stack version extends this with Spring Boot, MySQL, and web interface.
