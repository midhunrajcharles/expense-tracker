import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ExpenseManager class manages all expense operations
 * Demonstrates: ArrayList, HashMap, Collections, Methods, OOP principles
 */
public class ExpenseManager {
    private ArrayList<Expense> expenseList;
    private HashMap<Integer, Expense> expenseMap;
    private int nextId;
    private static final String[] CATEGORIES = {"Food", "Travel", "Shopping", "Utilities", "Others"};
    private static final String[] PAYMENT_METHODS = {"Cash", "Card", "UPI"};

    public ExpenseManager() {
        this.expenseList = new ArrayList<>();
        this.expenseMap = new HashMap<>();
        this.nextId = 1;
    }

    /**
     * Add a new expense
     * Validates mandatory fields and adds to both ArrayList and HashMap
     */
    public boolean addExpense(String title, String category, double amount, 
                              LocalDate expenseDate, String paymentMethod) {
        // Validation
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Title cannot be empty!");
            return false;
        }
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty!");
            return false;
        }
        if (!isValidCategory(category)) {
            System.out.println("Error: Invalid category! Choose from: " + Arrays.toString(CATEGORIES));
            return false;
        }
        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than zero!");
            return false;
        }
        if (expenseDate == null) {
            System.out.println("Error: Expense date cannot be empty!");
            return false;
        }
        if (!isValidPaymentMethod(paymentMethod)) {
            System.out.println("Error: Invalid payment method! Choose from: " + Arrays.toString(PAYMENT_METHODS));
            return false;
        }

        Expense expense = new Expense(nextId, title, category, amount, expenseDate, paymentMethod);
        expenseList.add(expense);
        expenseMap.put(nextId, expense);
        nextId++;
        
        System.out.println("✓ Expense added successfully! ID: " + expense.getExpenseId());
        return true;
    }

    /**
     * View all expenses
     */
    public void viewAllExpenses() {
        if (expenseList.isEmpty()) {
            System.out.println("No expenses found!");
            return;
        }

        System.out.println("\n========== ALL EXPENSES ==========");
        for (Expense expense : expenseList) {
            if (expense.isHighValue()) {
                System.out.println("⚠️  [HIGH VALUE] " + expense);
            } else {
                System.out.println(expense);
            }
        }
        System.out.println("==================================\n");
    }

    /**
     * Update an expense by ID
     */
    public boolean updateExpense(int id, String title, String category, double amount, 
                                 LocalDate expenseDate, String paymentMethod) {
        if (!expenseMap.containsKey(id)) {
            System.out.println("Error: Expense with ID " + id + " not found!");
            return false;
        }

        Expense expense = expenseMap.get(id);

        // Validation
        if (title != null && !title.trim().isEmpty()) {
            expense.setTitle(title);
        }
        if (category != null && !category.trim().isEmpty()) {
            if (!isValidCategory(category)) {
                System.out.println("Error: Invalid category!");
                return false;
            }
            expense.setCategory(category);
        }
        if (amount > 0) {
            expense.setAmount(amount);
        }
        if (expenseDate != null) {
            expense.setExpenseDate(expenseDate);
        }
        if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
            if (!isValidPaymentMethod(paymentMethod)) {
                System.out.println("Error: Invalid payment method!");
                return false;
            }
            expense.setPaymentMethod(paymentMethod);
        }

        System.out.println("✓ Expense updated successfully!");
        return true;
    }

    /**
     * Delete an expense by ID
     */
    public boolean deleteExpense(int id) {
        if (!expenseMap.containsKey(id)) {
            System.out.println("Error: Expense with ID " + id + " not found!");
            return false;
        }

        Expense expense = expenseMap.get(id);
        expenseList.remove(expense);
        expenseMap.remove(id);
        
        System.out.println("✓ Expense deleted successfully!");
        return true;
    }

    /**
     * Filter expenses by category
     */
    public void filterByCategory(String category) {
        List<Expense> filtered = expenseList.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No expenses found for category: " + category);
            return;
        }

        System.out.println("\n========== FILTERED BY CATEGORY: " + category.toUpperCase() + " ==========");
        for (Expense expense : filtered) {
            System.out.println(expense);
        }
        System.out.println("==========================================================\n");
    }

    /**
     * Search expenses by title
     */
    public void searchByTitle(String searchTerm) {
        List<Expense> results = expenseList.stream()
                .filter(e -> e.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No expenses found matching: " + searchTerm);
            return;
        }

        System.out.println("\n========== SEARCH RESULTS FOR: " + searchTerm + " ==========");
        for (Expense expense : results) {
            System.out.println(expense);
        }
        System.out.println("======================================================\n");
    }

    /**
     * View total expense summary
     */
    public void viewSummary() {
        if (expenseList.isEmpty()) {
            System.out.println("No expenses to summarize!");
            return;
        }

        double total = 0;
        HashMap<String, Double> categoryTotals = new HashMap<>();

        for (Expense expense : expenseList) {
            total += expense.getAmount();
            String cat = expense.getCategory();
            categoryTotals.put(cat, categoryTotals.getOrDefault(cat, 0.0) + expense.getAmount());
        }

        System.out.println("\n========== EXPENSE SUMMARY ==========");
        System.out.printf("Total Expenses: $%.2f%n", total);
        System.out.printf("Number of Expenses: %d%n", expenseList.size());
        System.out.println("\nCategory-wise Breakdown:");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            System.out.printf("  %s: $%.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("=====================================\n");
    }

    // Helper methods
    private boolean isValidCategory(String category) {
        for (String cat : CATEGORIES) {
            if (cat.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPaymentMethod(String method) {
        for (String pm : PAYMENT_METHODS) {
            if (pm.equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    public String[] getCategories() {
        return CATEGORIES;
    }

    public String[] getPaymentMethods() {
        return PAYMENT_METHODS;
    }
}
