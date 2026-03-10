package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ExpenseRepository - Data Access Layer
 * Extends JpaRepository for CRUD operations
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Custom query methods using Spring Data JPA

    /**
     * Find expenses by category
     */
    List<Expense> findByCategory(String category);

    /**
     * Find expenses by title containing search term (case-insensitive)
     */
    List<Expense> findByTitleContainingIgnoreCase(String searchTerm);

    /**
     * Find expenses by payment method
     */
    List<Expense> findByPaymentMethod(String paymentMethod);

    /**
     * Find all expenses ordered by expense date descending
     */
    List<Expense> findAllByOrderByExpenseDateDesc();

    /**
     * Find all expenses ordered by amount descending
     */
    List<Expense> findAllByOrderByAmountDesc();

    /**
     * Calculate total amount of all expenses
     */
    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double calculateTotalExpenses();

    /**
     * Calculate total amount by category
     */
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category = ?1")
    Double calculateTotalByCategory(String category);
}
