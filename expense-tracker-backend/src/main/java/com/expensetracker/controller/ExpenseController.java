package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ExpenseController - REST API endpoints
 * Handles HTTP requests and responses
 */
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") // Allow requests from frontend
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    /**
     * GET /api/expenses - Get all expenses
     */
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    /**
     * GET /api/expenses/{id} - Get expense by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/expenses - Create new expense
     */
    @PostMapping
    public ResponseEntity<Expense> createExpense(@Valid @RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    /**
     * PUT /api/expenses/{id} - Update expense
     */
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expenseDetails) {
        try {
            Expense updatedExpense = expenseService.updateExpense(id, expenseDetails);
            return ResponseEntity.ok(updatedExpense);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/expenses/{id} - Delete expense
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(Map.of("message", "Expense deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/expenses/filter/category/{category} - Filter by category
     */
    @GetMapping("/filter/category/{category}")
    public ResponseEntity<List<Expense>> filterByCategory(@PathVariable String category) {
        List<Expense> expenses = expenseService.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    /**
     * GET /api/expenses/search?q={searchTerm} - Search by title
     */
    @GetMapping("/search")
    public ResponseEntity<List<Expense>> searchByTitle(@RequestParam String q) {
        List<Expense> expenses = expenseService.searchExpensesByTitle(q);
        return ResponseEntity.ok(expenses);
    }

    /**
     * GET /api/expenses/summary - Get expense summary
     */
    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary() {
        Map<String, Object> summary = expenseService.getExpenseSummary();
        return ResponseEntity.ok(summary);
    }

    /**
     * GET /api/expenses/high-value - Get high-value expenses
     */
    @GetMapping("/high-value")
    public ResponseEntity<List<Expense>> getHighValueExpenses() {
        List<Expense> expenses = expenseService.getHighValueExpenses();
        return ResponseEntity.ok(expenses);
    }
}
