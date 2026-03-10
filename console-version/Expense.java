import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Expense class represents an individual expense entry
 * Demonstrates: Encapsulation, OOP principles
 */
public class Expense {
    private int expenseId;
    private String title;
    private String category; // Food / Travel / Shopping / Utilities / Others
    private double amount;
    private LocalDate expenseDate;
    private String paymentMethod; // Cash / Card / UPI
    private LocalDateTime createdDateTime;

    // Constructor
    public Expense(int expenseId, String title, String category, double amount, 
                   LocalDate expenseDate, String paymentMethod) {
        this.expenseId = expenseId;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.paymentMethod = paymentMethod;
        this.createdDateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Category: %s | Amount: $%.2f | Date: %s | Payment: %s | Created: %s",
                expenseId, title, category, amount, expenseDate, paymentMethod, createdDateTime);
    }

    // Method to check if expense is high-value (>= $500)
    public boolean isHighValue() {
        return amount >= 500.0;
    }
}
