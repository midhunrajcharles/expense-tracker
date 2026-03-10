package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ExpenseService - Business Logic Layer
 * Handles all business operations for expenses
 */
@Service
@Transactional
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * Create a new expense
     */
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * Get all expenses
     */
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAllByOrderByExpenseDateDesc();
    }

    /**
     * Get expense by ID
     */
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    /**
     * Update an existing expense
     */
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        expense.setTitle(expenseDetails.getTitle());
        expense.setCategory(expenseDetails.getCategory());
        expense.setAmount(expenseDetails.getAmount());
        expense.setExpenseDate(expenseDetails.getExpenseDate());
        expense.setPaymentMethod(expenseDetails.getPaymentMethod());

        return expenseRepository.save(expense);
    }

    /**
     * Delete an expense
     */
    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        expenseRepository.delete(expense);
    }

    /**
     * Filter expenses by category
     */
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    /**
     * Search expenses by title
     */
    public List<Expense> searchExpensesByTitle(String searchTerm) {
        return expenseRepository.findByTitleContainingIgnoreCase(searchTerm);
    }

    /**
     * Get expense summary
     */
    public Map<String, Object> getExpenseSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        List<Expense> allExpenses = expenseRepository.findAll();
        Double total = expenseRepository.calculateTotalExpenses();
        
        if (total == null) {
            total = 0.0;
        }

        summary.put("totalExpenses", total);
        summary.put("numberOfExpenses", allExpenses.size());

        // Category-wise breakdown
        Map<String, Double> categoryTotals = new HashMap<>();
        String[] categories = {"Food", "Travel", "Shopping", "Utilities", "Others"};
        
        for (String category : categories) {
            Double categoryTotal = expenseRepository.calculateTotalByCategory(category);
            if (categoryTotal != null) {
                categoryTotals.put(category, categoryTotal);
            }
        }
        
        summary.put("categoryBreakdown", categoryTotals);

        return summary;
    }

    /**
     * Get high-value expenses (>= 500)
     */
    public List<Expense> getHighValueExpenses() {
        return expenseRepository.findAll().stream()
                .filter(Expense::isHighValue)
                .toList();
    }
}
