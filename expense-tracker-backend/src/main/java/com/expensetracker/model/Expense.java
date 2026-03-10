package com.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Expense Entity - JPA mapped to database table
 * Demonstrates: OOP, JPA annotations, validation
 */
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotBlank(message = "Category is mandatory")
    @Pattern(regexp = "Food|Travel|Shopping|Utilities|Others", 
             message = "Category must be one of: Food, Travel, Shopping, Utilities, Others")
    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @NotNull(message = "Amount is mandatory")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull(message = "Expense date is mandatory")
    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @NotBlank(message = "Payment method is mandatory")
    @Pattern(regexp = "Cash|Card|UPI", 
             message = "Payment method must be one of: Cash, Card, UPI")
    @Column(name = "payment_method", nullable = false, length = 20)
    private String paymentMethod;

    @Column(name = "created_date_time", nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    // Default constructor
    public Expense() {
    }

    // Constructor with parameters
    public Expense(String title, String category, Double amount, 
                   LocalDate expenseDate, String paymentMethod) {
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.paymentMethod = paymentMethod;
    }

    // Automatically set creation timestamp
    @PrePersist
    protected void onCreate() {
        createdDateTime = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    // Business method
    public boolean isHighValue() {
        return amount != null && amount >= 500.0;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                ", expenseDate=" + expenseDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", createdDateTime=" + createdDateTime +
                '}';
    }
}
